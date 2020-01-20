package com.where.soul.bill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.where.soul.bill.dto.BillDTO;
import com.where.soul.bill.entity.Bill;
import com.where.soul.bill.entity.Tag;
import com.where.soul.bill.entity.Type;
import com.where.soul.bill.mapper.BillMapper;
import com.where.soul.bill.mapper.TagMapper;
import com.where.soul.bill.mapper.TypeMapper;
import com.where.soul.bill.service.IBillService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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

    public BillServiceImpl(BillMapper billMapper, TagMapper tagMapper, TypeMapper typeMapper) {
        this.billMapper = billMapper;
        this.tagMapper = tagMapper;
        this.typeMapper = typeMapper;
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
}
