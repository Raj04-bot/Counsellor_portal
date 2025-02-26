package com.counsellor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.counsellor.entity.Counsellor;
import java.util.List;


@Repository
public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {

	public Counsellor  findByEmail(String email);
	
	public Counsellor findByEmailAndPswd(String email, String pswd);

}
