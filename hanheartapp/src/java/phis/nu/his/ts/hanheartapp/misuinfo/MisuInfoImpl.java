package phis.nu.his.ts.hanheartapp.misuinfo;

import phis.nu.his.ts.hanheartmgr.misuinfomgt.MisuInfoMgt;



import phis.nuframe.context.ContextAwareService;
import phis.nuframe.exception.UserException;
import phis.nuframe.vo.ValueObject;
import phis.nuframe.vo.ValueObjectAssembler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MisuInfoImpl extends ContextAwareService implements MisuInfo {
	
	private static final Logger log = LoggerFactory.getLogger(MisuInfoImpl.class);

	private MisuInfoMgt misuInfoMgt = null;

	public void setMisuInfoMgt(MisuInfoMgt mgt) {
		this.misuInfoMgt = mgt;
	}
	
	@Override
	public ValueObjectAssembler reqGetMisuTypeList(ValueObjectAssembler pVOs) {
		
		ValueObjectAssembler retVOs = new ValueObjectAssembler(); //반환할 데이터형 생성
		ValueObject pVO = null; //파라미터로 받아온 값
		//pVO.dumpTable(log);//로그 생성

		try {
			ValueObject getVO = misuInfoMgt.misuTypeList(pVO);
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

	@Override
	public ValueObjectAssembler reqGetMisuList(ValueObjectAssembler pVOs) {
		// TODO Auto-generated method stub
		ValueObjectAssembler retVOs = new ValueObjectAssembler();
		ValueObject pVO = pVOs.get("req"); //파라미터로 받아온 값
		pVO.dumpTable(log);
		
		try {
			ValueObject getVO = misuInfoMgt.getMisuList(pVO);
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

	@Override
	public ValueObjectAssembler reqGetMisuDepList(ValueObjectAssembler pVOs) {
		// TODO Auto-generated method stub
		ValueObjectAssembler retVOs = new ValueObjectAssembler();
		ValueObject pVO = pVOs.get("req"); //파라미터로 받아온 값
		pVO.dumpTable(log);
		
		
		ValueObject getVO = misuInfoMgt.getDepList(pVO);
		retVOs.set("ret", getVO);
		return retVOs;
	}

	@Override
	public ValueObjectAssembler reqSetMisuInfo(ValueObjectAssembler pVOs) {
		
		ValueObjectAssembler retVOs = new ValueObjectAssembler();
		
		ValueObject pVO = pVOs.get("req");
		ValueObject listVO = pVOs.get("list");
		
		listVO.dumpTable(log);

		try {
			
			for(int i = 0; i < listVO.size(); i++){
				String status = listVO.getString(i, "status");
				if("I".equals(status)){
					misuInfoMgt.insMisuInfo(listVO.getRowAsVo(i));
				} else if("U".equals(status)){
					misuInfoMgt.insMisuInfo(listVO.getRowAsVo(i));
				} else if ("D".equals(status)){
					misuInfoMgt.delMisuInfo(listVO.getRowAsVo(i));
				}
			}
			
			
			
			ValueObject getVO = misuInfoMgt.getMisuList(pVO);
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
