package phis.nu.his.ts.hanheartapp.screeninfo;

import phis.nuframe.vo.ValueObjectAssembler;

public interface ScreenInfo {
	
	ValueObjectAssembler reqGetScreenList(ValueObjectAssembler pVOs);
	
	ValueObjectAssembler reqSetScreenInfo(ValueObjectAssembler pVOs);

}
