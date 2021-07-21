package com.cdwintech.app.support;

import top.bettercode.simpleframework.data.jpa.PageController;
import com.cdwintech.app.web.SerializationViews;
import com.cdwintech.app.security.SecurityUserHelper;

/**
 * @author Peter Wu
 */
public class AppController extends PageController implements SerializationViews {

  /**
   * @return 登录客户主键
   */
  protected Long getCustId() {
    return SecurityUserHelper.getId();
  }


}
