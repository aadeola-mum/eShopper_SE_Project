package cs425.team4.eshopper.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cs425.team4.eshopper.models.Merchant;


public interface MerchantRepository extends UserBaseRepository<Merchant> {

	Page<Merchant> findByApproved(boolean approved, Pageable pageable);
    
}
