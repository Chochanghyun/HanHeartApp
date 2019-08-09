package phis.nu.his.ts.hanheartapp.submitinfo;

import phis.nu.his.ts.hanheartmgr.submitinfomgt.SubmitInfoMgt;

import phis.nuframe.context.ContextAwareService;
import phis.nuframe.exception.UserException;
import phis.nuframe.vo.ValueObject;
import phis.nuframe.vo.ValueObjectAssembler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubmitInfoImpl extends ContextAwareService implements SubmitInfo {
	
	private static final Logger log = LoggerFactory.getLogger( SubmitInfoImpl.class);

	private SubmitInfoMgt submitInfoMgt = null;

	public void setSubmitInfoMgt(SubmitInfoMgt mgt) {
		this.submitInfoMgt = mgt;
	}
	
	@Override
	public ValueObjectAssembler reqGetSubmitList(ValueObjectAssembler pVOs) {

		ValueObjectAssembler retVOs = new ValueObjectAssembler();
		
		ValueObject pVO = pVOs.get("req");
		System.out.println("�޼ҵ� ��");
		try {
			System.out.println("@@@@@@@@@@");
			System.out.println(pVO);
			
			
			ValueObject getVO = submitInfoMgt.getSubmitList(pVO);
			retVOs.set("ret", getVO);
			System.out.println("123213213213213");

		} catch (UserException ex) {
			setRollbackOnly();
			throw ex;
		} catch (Exception ex) {
			setRollbackOnly();
			throw new UserException("nu.error.09003", ex);
		}
		
		return retVOs;
	}

	@Override
	public ValueObjectAssembler reqSetSubmitInfo(ValueObjectAssembler pVOs) {
		
		ValueObjectAssembler retVOs = new ValueObjectAssembler();
		
		ValueObject pVO = pVOs.get("req");
		ValueObject listVO = pVOs.get("list");
		
		listVO.dumpTable(log);

		try {
			
			for(int i = 0; i < listVO.size(); i++){
				String status = listVO.getString(i, "status");
				if("I".equals(status)){
					submitInfoMgt.insSubmitInfo(listVO.getRowAsVo(i));
				} else if("U".equals(status)){
					submitInfoMgt.setSubmitInfo(listVO.getRowAsVo(i));
				} else if ("D".equals(status)){
					submitInfoMgt.delSubmitInfo(listVO.getRowAsVo(i));
				}
			}
			
			
			
			ValueObject getVO = submitInfoMgt.getSubmitList(pVO);
			retVOs.set("ret", getVO);

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
