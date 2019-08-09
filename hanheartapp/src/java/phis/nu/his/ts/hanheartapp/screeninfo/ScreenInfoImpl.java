package phis.nu.his.ts.hanheartapp.screeninfo;


import phis.nu.his.ts.hanheartmgr.screeninfomgt.ScreenInfoMgt;

import phis.nuframe.context.ContextAwareService;
import phis.nuframe.exception.UserException;
import phis.nuframe.vo.ValueObject;
import phis.nuframe.vo.ValueObjectAssembler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScreenInfoImpl extends ContextAwareService implements ScreenInfo {
	
	private static final Logger log = LoggerFactory.getLogger( ScreenInfoImpl.class);

	private ScreenInfoMgt screenInfoMgt = null;

	public void setScreenInfoMgt(ScreenInfoMgt mgt) {
		this.screenInfoMgt = mgt;
	}

	@Override
	public ValueObjectAssembler reqGetScreenList(ValueObjectAssembler pVOs) {

		ValueObjectAssembler retVOs = new ValueObjectAssembler(); //��ȯ�� �������� ����
		ValueObject pVO = pVOs.get("req"); //�Ķ���ͷ� �޾ƿ� ��
		pVO.dumpTable(log);//�α� ����

		try {
			ValueObject getVO = screenInfoMgt.getScreenList(pVO);
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
	public ValueObjectAssembler reqSetScreenInfo(ValueObjectAssembler pVOs) {
	
		ValueObjectAssembler retVOs = new ValueObjectAssembler();
		
		ValueObject pVO = pVOs.get("req");
		ValueObject listVO = pVOs.get("list");
		
		listVO.dumpTable(log);
		
		System.out.println("�޼ҵ� ����");
		try {
			
			for(int i = 0; i < listVO.size(); i++){
				System.out.println("for��");
				String status = listVO.getString(i, "status");
				
				if("I".equals(status)){
					System.out.println("IIIIIIIIII");
					screenInfoMgt.insScreenInfo(listVO.getRowAsVo(i));
				} else if("U".equals(status)){
					System.out.println("UUUUUUUUUU");
					screenInfoMgt.setScreenInfo(listVO.getRowAsVo(i));
				} else if ("D".equals(status)){
					System.out.println("DDDDDDDDDDD");
					screenInfoMgt.delScreenInfo(listVO.getRowAsVo(i));
				}
			}
			
			
			
			ValueObject getVO = screenInfoMgt.getScreenList(pVO);
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
