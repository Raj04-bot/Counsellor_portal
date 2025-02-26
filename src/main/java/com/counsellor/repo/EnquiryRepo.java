package com.counsellor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.counsellor.entity.Enquiry;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {
	
	@Query(value="selet * from enq_tbl where counsellor_id=:counsellorId",nativeQuery = true)
	public List<Enquiry> getEnquiresByCounsellorId(Integer counsellorId);

}
