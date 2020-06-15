package security.service;


import security.model.Details;

/**
 * Provider of current system user login.
 */
public interface UserInfoProvider {
  /**
   * Returns {@link Details} with current system user login.
   *
   * @return {@link Details} with current system user login
   */
  Details getCurrentUserDetail();
}
