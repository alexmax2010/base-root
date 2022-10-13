package com.base.security.audit;

/**
 * IKeycloakUserInfo.
 *
 * @author components on 13/10/2022.
 * @version 1.0
 */
public interface IKeycloakUserInfo {

    String getUserId();

    String getUserName();

    Integer getCompanyCode();

    String getIp();


}
