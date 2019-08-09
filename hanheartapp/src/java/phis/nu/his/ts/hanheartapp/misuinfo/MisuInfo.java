package phis.nu.his.ts.hanheartapp.misuinfo;

import phis.nuframe.vo.ValueObjectAssembler;

public interface MisuInfo {
	
	ValueObjectAssembler reqGetMisuTypeList(ValueObjectAssembler pVOs);
	ValueObjectAssembler reqGetMisuList(ValueObjectAssembler pVOs);	
	ValueObjectAssembler reqGetMisuDepList(ValueObjectAssembler pVOs);
	ValueObjectAssembler reqSetMisuInfo(ValueObjectAssembler pVOs);
	
}
