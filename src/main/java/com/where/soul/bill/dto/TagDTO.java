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

    private List<TagDTO> children;

    public TagDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagDTO(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }

    public TagDTO build(List<Tag> tagList) {
        this.setChildren(findChildren(tagList));
        return this;
    }

    public List<TagDTO> buildTagDtoList(List<Tag> tagList) {
        List<TagDTO> tagDtoList = new ArrayList<>();
        if (tagList != null) {
            for (int i = 0; i < tagList.size(); i++) {
                Tag tag = tagList.get(i);
                if (tag.getParentId() == null) {
                    tagList.remove(tag);
                    tagDtoList.add(new TagDTO(tag).build(tagList));
                }
            }
        }

        return tagDtoList;
    }

    public List<TagDTO> findChildren(List<Tag> tagList) {
        List<TagDTO> list = new ArrayList<>();
        if (tagList != null) {
            for (Tag item : tagList) {
                if (this.getId().equals(item.getParentId())) {
                    list.add(new TagDTO(item).build(tagList));
                }
            }
        }
        return list;
    }
}
