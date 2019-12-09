package com.where.soul.bill.dto;

import com.where.soul.bill.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lw
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {

    private Integer id;

    private String name;

    private TagDTO children;

    public TagDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<TagDTO> buildTagDTOList(List<Tag> tagList) {
        List<TagDTO> tagDtoList = new ArrayList<>();
        if (tagList != null) {
            for (int i = 0; i < tagList.size(); i++) {
                Tag tag = tagList.get(i);
                if (tag.getParentId() == null) {
                    tagList.remove(tag);
                    tagDtoList.add(findChildren(tag, tagList));
                }
            }
        }

        return tagDtoList;
    }

    private TagDTO findChildren(Tag tag, List<Tag> tagList) {
        TagDTO child = new TagDTO(tag.getId(), tag.getName());
        if (tagList != null) {
            for (Tag item : tagList) {
                if (tag.getId().equals(item.getParentId())){
                    child.setChildren(findChildren(item, tagList));
                }
            }
        } else {
            return child;
        }

        return child;
    }
}
