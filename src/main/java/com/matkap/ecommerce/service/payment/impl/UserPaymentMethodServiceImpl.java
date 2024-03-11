package com.matkap.ecommerce.service.payment.impl;

import com.matkap.ecommerce.dto.requestDto.payment.UserPaymentMethodRequestDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.payment.PaymentType;
import com.matkap.ecommerce.model.payment.UserPaymentMethod;
import com.matkap.ecommerce.model.user.SiteUser;
import com.matkap.ecommerce.repository.payment.PaymentTypeRepository;
import com.matkap.ecommerce.repository.payment.UserPaymentMethodRepository;
import com.matkap.ecommerce.service.payment.UserPaymentMethodService;
import com.matkap.ecommerce.service.user.SiteUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPaymentMethodServiceImpl implements UserPaymentMethodService {

private final UserPaymentMethodRepository userPaymentMethodRepository;
private final PaymentTypeRepository paymentTypeRepository;
private final SiteUserService siteUserService;


    public UserPaymentMethodServiceImpl(UserPaymentMethodRepository userPaymentMethodRepository, PaymentTypeRepository paymentTypeRepository, SiteUserService siteUserService) {
        this.userPaymentMethodRepository = userPaymentMethodRepository;
        this.paymentTypeRepository = paymentTypeRepository;
        this.siteUserService = siteUserService;
    }


    @Override
    public UserPaymentMethod createUserPaymentMethod(UserPaymentMethodRequestDto userPaymentMethodRequestDto) {
        UserPaymentMethod userPaymentMethod = new UserPaymentMethod();
        Long siteUserId = userPaymentMethodRequestDto.getSiteUserId();
        SiteUser siteUser = siteUserService.getSiteUser(siteUserId);
        userPaymentMethod.setSiteUser(siteUser);
        PaymentType paymentType = getPaymentType(userPaymentMethodRequestDto.getPaymentTypeId());
        userPaymentMethod.setPaymentType(paymentType);
        userPaymentMethod.setProvider(userPaymentMethodRequestDto.getProvider());
        userPaymentMethod.setAccountNumber(userPaymentMethodRequestDto.getAccountNumber());
        userPaymentMethod.setExpiryDate(userPaymentMethodRequestDto.getExpiryDate());
        if (userPaymentMethod.getDefaultPayment() == Boolean.TRUE){
            userPaymentMethodRepository.updateDefaultPaymentBySiteUser(siteUserId);
            userPaymentMethod.setDefaultPayment(Boolean.TRUE);
        }else{
            userPaymentMethod.setDefaultPayment(Boolean.FALSE);
        }

        return userPaymentMethodRepository.save(userPaymentMethod);
    }

    @Override
    public List<UserPaymentMethod> getUserPaymentMethods() {
        return userPaymentMethodRepository.findAll();
    }

    @Override
    public List<PaymentType> getPaymentTypes() {
        return paymentTypeRepository.findAll();
    }

    @Override
    public UserPaymentMethod getUserPaymentMethodById(Long userPaymentMethodId) {
        return getUserPaymentMethod(userPaymentMethodId);
    }

    @Override
    public UserPaymentMethod getUserPaymentMethod(Long userPaymentMethodId) {
        return userPaymentMethodRepository.findById(userPaymentMethodId).orElseThrow(
                () -> new EntityNotFoundException(userPaymentMethodId, UserPaymentMethod.class));
    }

    @Override
    public PaymentType getPaymentType(Long paymentTypeId) {
        return paymentTypeRepository.findById(paymentTypeId).orElseThrow(
                () -> new EntityNotFoundException(paymentTypeId, PaymentType.class));
    }

    @Override
    public void deleteUserPaymentMethod(Long userPaymentMethodId) {
        UserPaymentMethod userPaymentMethod = getUserPaymentMethod(userPaymentMethodId);
        userPaymentMethodRepository.delete(userPaymentMethod);
    }

    @Override
    public UserPaymentMethod editUserPaymentMethod(Long userPaymentMethodId, UserPaymentMethodRequestDto userPaymentMethodRequestDto) {
        UserPaymentMethod userPaymentMethod = getUserPaymentMethod(userPaymentMethodId);
        Long siteUserId = userPaymentMethodRequestDto.getSiteUserId();
        SiteUser siteUser = siteUserService.getSiteUser(siteUserId);
        userPaymentMethod.setSiteUser(siteUser);
        PaymentType paymentType = getPaymentType(userPaymentMethodRequestDto.getPaymentTypeId());
        userPaymentMethod.setPaymentType(paymentType);
        userPaymentMethod.setProvider(userPaymentMethodRequestDto.getProvider());
        userPaymentMethod.setAccountNumber(userPaymentMethodRequestDto.getAccountNumber());
        userPaymentMethod.setExpiryDate(userPaymentMethodRequestDto.getExpiryDate());
        if (userPaymentMethod.getDefaultPayment() == Boolean.TRUE){
            userPaymentMethodRepository.updateDefaultPaymentBySiteUser(siteUserId);
            userPaymentMethod.setDefaultPayment(Boolean.TRUE);
        }else{
            userPaymentMethod.setDefaultPayment(Boolean.FALSE);
        }
        return userPaymentMethodRepository.save(userPaymentMethod);
    }

    @Override
    public void setDefaultPayment(Long userPaymentMethodId) {
        UserPaymentMethod userPaymentMethod = getUserPaymentMethod(userPaymentMethodId);
        Long siteUserId = userPaymentMethod.getSiteUser().getId();
        userPaymentMethodRepository.updateDefaultPaymentBySiteUser(siteUserId);

        userPaymentMethod.setDefaultPayment(Boolean.TRUE);
        userPaymentMethodRepository.save(userPaymentMethod);
    }
}
