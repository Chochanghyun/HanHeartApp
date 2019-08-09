package phis.nu.his.ts.hanheartapp.maintinfo;

import phis.nu.his.ts.hanheartmgr.maintinfomgt.MaintInfoMgt;

import phis.nuframe.context.ContextAwareService;
import phis.nuframe.exception.UserException;
import phis.nuframe.vo.ValueObject;
import phis.nuframe.vo.ValueObjectAssembler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaintInfoImpl extends ContextAwareService implements MaintInfo {
	
	private static final Logger log = LoggerFactory.getLogger(MaintInfoImpl.class);
	
	private MaintInfoMgt maintInfoMgt = null;

	  public void setMaintInfoMgt(MaintInfoMgt maintInfoMgt){
	      this.maintInfoMgt = maintInfoMgt;
	   }	
	
	@Override
	public ValueObjectAssembler reqGetBscd(ValueObjectAssembler pVOs) {
		
		ValueObjectAssembler retVOs = new ValueObjectAssembler(); //��ȯ�� �������� ����
		ValueObject pVO = null; //�Ķ���ͷ� �޾ƿ� ��
		//pVO.dumpTable(log);//�α� ����

		try {
			ValueObject getVO = maintInfoMgt.getBscd(pVO);
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
	public ValueObjectAssembler reqGetMaintList(ValueObjectAssembler pVOs) {
		System.out.println("reqGetMaintList");
		ValueObjectAssembler retVOs = new ValueObjectAssembler();
		ValueObject pVO = pVOs.get("req"); //�Ķ���ͷ� �޾ƿ� ��
		pVO.dumpTable(log);
		System.out.println(pVO.getString("hs_grup"));
		try {
			ValueObject getVO = maintInfoMgt.getMaintList(pVO);
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
