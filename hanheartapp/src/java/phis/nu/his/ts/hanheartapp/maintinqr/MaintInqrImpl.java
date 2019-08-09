package phis.nu.his.ts.hanheartapp.maintinqr;

import phis.nu.his.ts.hanheartmgr.maintinqrmgt.MaintInqrMgt;

import phis.nuframe.context.ContextAwareService;
import phis.nuframe.exception.UserException;
import phis.nuframe.vo.ValueObject;
import phis.nuframe.vo.ValueObjectAssembler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaintInqrImpl extends ContextAwareService implements MaintInqr {

	private static final Logger log = LoggerFactory.getLogger(MaintInqrImpl.class);

	private MaintInqrMgt maintInqrMgt = null;

	public void setMaintInqrMgt(MaintInqrMgt maintInqrMgt) {
		this.maintInqrMgt = maintInqrMgt;
	}

	@Override
	public ValueObjectAssembler reqGetBscd(ValueObjectAssembler pVOs) {
		
		ValueObjectAssembler retVOs = new ValueObjectAssembler(); //��ȯ�� �������� ����
		ValueObject pVO = null; //�Ķ���ͷ� �޾ƿ� ��
		//pVO.dumpTable(log);//�α� ����

		try {
			ValueObject getVO = maintInqrMgt.getBscd(pVO);
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
	public ValueObjectAssembler reqGetDeptTree(ValueObjectAssembler pVOs) {
		System.out.println("reqDeptTree");
		
		ValueObjectAssembler retVOs = new ValueObjectAssembler();
		ValueObject pVO = pVOs.get("req"); //�Ķ���ͷ� �޾ƿ� ��
		//System.out.println(pVO.getString("hs_kind"));
		pVO.dumpTable(log);
		
		try {
			ValueObject getVO = maintInqrMgt.getDeptTree(pVO);
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
