package com.where.soul.bill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.where.soul.bill.dto.BillDTO;
import com.where.soul.bill.entity.Bill;
import com.where.soul.bill.entity.Tag;
import com.where.soul.bill.entity.TagMerge;
import com.where.soul.bill.entity.Type;
import com.where.soul.bill.mapper.BillMapper;
import com.where.soul.bill.mapper.TagMapper;
import com.where.soul.bill.mapper.TagMergeMapper;
import com.where.soul.bill.mapper.TypeMapper;
import com.where.soul.bill.service.IBillService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 账单表 服务实现类
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillService {

    private final BillMapper billMapper;
    private final TagMapper tagMapper;
    private final TypeMapper typeMapper;
    private final TagMergeMapper tagMergeMapper;

    public BillServiceImpl(BillMapper billMapper, TagMapper tagMapper, TypeMapper typeMapper, TagMergeMapper tagMergeMapper) {
        this.billMapper = billMapper;
        this.tagMapper = tagMapper;
        this.typeMapper = typeMapper;
        this.tagMergeMapper = tagMergeMapper;
    }

    @Override
    public List<BillDTO> selectBills(Integer userId, Integer tagId, Integer typeId, String startTime, String endTime, Integer pageSize, Integer lastId) {
        List<BillDTO> billDTOList = new ArrayList<>();
        List<Bill> bills = billMapper.selectBills(userId, tagId, typeId, startTime, endTime, pageSize, lastId);
        bills.forEach(b -> {
            BillDTO billDTO = new BillDTO();
            BeanUtils.copyProperties(b, billDTO);
            BillDTO.BillTagDTO billTagDTO = null;
            BillDTO.BillTypesDTO billTypesDTO = null;
            if (b.getTagId() != null) {
                Tag tag = tagMapper.selectById(b.getTagId());
                if (tag != null) {
                    billTagDTO = new BillDTO.BillTagDTO(b.getTagId(), tag.getName());
                }
            }
            if (b.getTypeId() != null) {
                Type type = typeMapper.selectById(b.getTypeId());
                if (type != null) {
                    billTypesDTO = new BillDTO.BillTypesDTO(b.getTypeId(), type.getName());
                }
            }

            int i = billDTOList.indexOf(billDTO);
            if (i != -1) {
                if (billTagDTO != null) {
                    billDTOList.get(i).getTags().add(billTagDTO);
                }
                if (billTypesDTO != null) {
                    billDTOList.get(i).getTypes().add(billTypesDTO);
                }
            } else {
                if (billTagDTO != null) {
                    billDTO.getTags().add(billTagDTO);
                }
                if (billTypesDTO != null) {
                    billDTO.getTypes().add(billTypesDTO);
                }
                billDTOList.add(billDTO);
            }
        });
        return billDTOList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addBill(Bill bill, List<Tag> tags) {
        bill.setCreateTime(LocalDateTime.now());
        bill.setUpdateTime(LocalDateTime.now());
        int insert = billMapper.insert(bill);
        Integer id = bill.getId();
        if (insert > 0 && tags != null && id != null) {
            tags.forEach(tag -> {
                insertTagMerge(id, tag);
            });
        }

        return insert;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer updateBill(Bill bill, List<Tag> tags) {
        bill.setCreateTime(LocalDateTime.now());
        bill.setUpdateTime(LocalDateTime.now());
        int i = billMapper.updateById(bill);
        Integer id = bill.getId();
        tagMergeMapper.delete(new QueryWrapper<TagMerge>().eq("bill_id", id));
        if (tags != null && id != null) {
            tags.forEach(tag -> {
                insertTagMerge(id, tag);
            });
        }

        return i;
    }

    private void insertTagMerge(Integer billId, Tag tag) {
        TagMerge tagMerge = new TagMerge();
        tagMerge.setBillId(billId);
        tagMerge.setBillTagId(tag.getId());
        tagMerge.setCreateTime(LocalDateTime.now());
        tagMerge.setUpdateTime(LocalDateTime.now());
        tagMergeMapper.insert(tagMerge);
    }
}
