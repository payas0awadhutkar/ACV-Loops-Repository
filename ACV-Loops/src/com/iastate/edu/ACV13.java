package com.iastate.edu;

import java.util.List;

public class ACV13 {
	
	public void post(List<Filter> FiltersToApply) {
		for(Filter filter: FiltersToApply) {
			if(filter != null) {
				applyFilter(filter);
			}
		}
	}
	
	private void applyFilter(Filter filter) {
		// code excluded
		// with sufficient number of filters applied, it causes problems
	}

	class Filter {
		// code excluded
		// not necessary to detect suspicious loop
	}
}
