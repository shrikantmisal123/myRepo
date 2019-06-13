package com.usa.federal.gov.ssa.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.usa.federal.gov.ssa.entity.SsnMasterEntity;
/** this repository is used for direct transaction with an database table
 * @author shrikant
 *
 */
@Repository("ssnMasterRepository")
public interface SsnMasterRepository extends JpaRepository<SsnMasterEntity, Serializable>{

}
