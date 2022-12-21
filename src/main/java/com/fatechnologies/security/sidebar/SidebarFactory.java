package com.fatechnologies.security.sidebar;

import com.fatechnologies.security.adapter.mapper.factory.Factory;

/**
 *
 * @author Assagou Fabrice 2022-03-06
 */
public class SidebarFactory implements Factory<Sidebar, SidebarDto> {

	@Override
	public SidebarDto factory(Sidebar source) {
		return new SidebarDto(source.getId());
	}	
	
}
