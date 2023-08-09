package cn.gnaixeuy.noteservice.entity;

import cn.gnaixeuy.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * redbook-back
 *
 * @author GnaixEuy
 * @version 1.0
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "note", autoResultMap = true)
public class Note extends BaseEntity {

    // 资源类型
    @TableField(value = "resourceType")
    private String resourceType;
    // 资源文件id列表
    @TableField(value = "fileIds", typeHandler = JacksonTypeHandler.class)
    private List<String> fileIds;
    // 笔记标题
    @TableField(value = "title")
    private String title;
    // 笔记内容描述
    @TableField(value = "description")
    private String description;

}