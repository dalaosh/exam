package com.mc.escommon.entity.exam;

import cn.hutool.core.annotation.Alias;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author machao
 * * @datetime 2025/3/28 20:06
 * * inform
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "题库申请")
public class QuestionAppleInCourse {
    @Schema(description = "token参数")
    private String token;
}
