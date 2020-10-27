package com.seohan.auth.Controller;

import com.seohan.auth.Dto.Account;
import com.seohan.auth.Repository.OrgUserRepository;
import com.seohan.auth.Service.AccountAdapter;
import com.seohan.auth.Service.AccountService;
import com.seohan.common.Service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/accounts")
@Slf4j
@RestController
class AccountController { 

	@Autowired
	private AccountService accountService; 

	
	@GetMapping
	@Description("로그인 사용자 정보가져오기")
	public ResponseEntity<Account> getAccount(@AuthenticationPrincipal AccountAdapter adapter) {

		if (adapter == null) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}

		Optional<Account> optionalAccount = accountService.findByAccountId(adapter.getAccount().getAccountid());
		Account account = optionalAccount.get();
		return ResponseEntity.ok().body(account);
	}
	@PostMapping
	@Description("회원가입")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {

		Optional<Account> byAccountId = accountService.findByAccountId(account.getAccountid());

		// 이미 존재하는 아이디
		if (byAccountId.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
		return new ResponseEntity<>(HttpStatus.OK);
	} 
}
