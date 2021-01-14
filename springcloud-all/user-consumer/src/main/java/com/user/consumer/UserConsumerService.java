package com.user.consumer;

import com.api.userapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "provider", fallbackFactory = UserProviderBackFactory.class)
public interface UserConsumerService extends UserApi {
}
