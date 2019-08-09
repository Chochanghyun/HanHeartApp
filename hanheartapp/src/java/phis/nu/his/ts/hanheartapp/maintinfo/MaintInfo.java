package phis.nu.his.ts.hanheartapp.maintinfo;

import phis.nuframe.vo.ValueObjectAssembler;

public interface MaintInfo {
	ValueObjectAssembler reqGetBscd(ValueObjectAssembler pVOs);
	ValueObjectAssembler reqGetMaintList(ValueObjectAssembler pVOs);
}
