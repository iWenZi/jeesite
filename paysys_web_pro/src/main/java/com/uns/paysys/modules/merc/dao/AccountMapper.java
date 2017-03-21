package com.uns.paysys.modules.merc.dao;

import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.Account;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.merc.form.AssignForm;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface AccountMapper {



    
    public List<Account> accounts(Account account);
    
    public List<Account> findAccountBySmId(BigDecimal id);
    
    Account findById(BigDecimal id);
   
}