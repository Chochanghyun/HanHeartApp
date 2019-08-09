package phis.nu.his.ts.hanheartapp.userinqr;

import phis.nu.his.ts.hanheartmgr.userinqrmgt.UserInqrMgt;

import phis.nuframe.context.ContextAwareService;
import phis.nuframe.exception.UserException;
import phis.nuframe.vo.ValueObject;
import phis.nuframe.vo.ValueObjectAssembler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserInqrImpl extends ContextAwareService implements UserInqr {
	
	private static final Logger log = LoggerFactory.getLogger(UserInqrImpl.class);
	private UserInqrMgt userInqrMgt = null;
	
	public void setUserInqrMgt(UserInqrMgt userInqrMgt){
		this.userInqrMgt = userInqrMgt;
	}
	
	@Override
	public ValueObjectAssembler reqGetDeptUser(ValueObjectAssembler pVOs) {
		// TODO Auto-generated method stub
		
		ValueObjectAssembler retVOs = new ValueObjectAssembler();
		ValueObject pVO = pVOs.get("req");
		
		System.out.println("data=====>" + pVO.getString("srch_scrn_type") + pVO.getString("srch_scrn_cd") + pVO.getString("srch_scrn_nm"));
		
		
		if (pVO.getString("srch_scrn_cd").isEmpty() && pVO.getString("srch_scrn_nm").isEmpty()) {
			setRollbackOnly();
			throw new UserException("조회조건을 입력하시오");
		}
		else if (pVO.getString("srch_scrn_type").equals("pos")) {
			
			pVO.dumpTable(log);//로그 생성
			log.info("dkjdlkjflkdflk");
			System.out.println("pppoooossssss");
			
			try {
				ValueObject getVO = userInqrMgt.posUserList(pVO);
				retVOs.set("ret", getVO);
			} catch (UserException ex) {
				setRollbackOnly();
				throw ex;
			} catch (Exception ex) {
				setRollbackOnly();
				throw new UserException("nu.error.09003", ex);
			}

		}else if(pVO.getString("srch_scrn_type").equals("dut")){
			pVO.dumpTable(log);//로그 생성
			log.info("dkjdlkjflkdflk");
			System.out.println("dddduuuuuutttt");
			
			try {
				ValueObject getVO = userInqrMgt.dutUserList(pVO);
				retVOs.set("ret", getVO);
			} catch (UserException ex) {
				setRollbackOnly();
				throw ex;
			} catch (Exception ex) {
				setRollbackOnly();
				throw new UserException("nu.error.09003", ex);
			}
		}
		
		return retVOs;
	}

	@Override
	public ValueObjectAssembler reqGetUserDutyHist(ValueObjectAssembler pVOs) {
		// TODO Auto-generated method stub
		ValueObjectAssembler retVOs = new ValueObjectAssembler();
		ValueObject pVO = pVOs.get("hist_req");

		pVO.dumpTable(log);//로그 생성
		log.info("dkjdlkjflkdflk");
		
		System.out.println(pVO.getString("userid"));
		
		try {
			ValueObject getVO = userInqrMgt.getUserDutyHist(pVO);
			retVOs.set("hist_ret", getVO);
		} catch (UserException ex) {
			setRollbackOnly();
			throw ex;
		} catch (Exception ex) {
			setRollbackOnly();
			throw new UserException("nu.error.09003", ex);
		}
		return retVOs;
	}

}
