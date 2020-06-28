package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UpdateAppversion
 * @Description TODO
 * @date 2020-06-12 15:38
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppVersionDto {
  private Long pkAppVersionId;
  private String appType;
  private String downloadLink;
  private String versionDescription;
  private String maxVersion;
  private String currentVersion;
}
