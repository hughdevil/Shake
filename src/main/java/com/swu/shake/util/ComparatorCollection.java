package com.swu.shake.util;

import java.util.Comparator;

import com.swu.shake.model.Collection;

/**
 * 收藏商品日期的排序比较类
 *
 */
public class ComparatorCollection implements Comparator<Collection> {

	public int compare(Collection arg0, Collection arg1) {
		Collection coll0 = (Collection) arg0;
		Collection coll1 = (Collection) arg1;

		// 比较收藏的日期
		int flag = coll0.getMarkDate().compareTo(coll1.getMarkDate());
		if (flag == 0) {
			return 0;
		} else {
			return -flag;
		}
	}

}