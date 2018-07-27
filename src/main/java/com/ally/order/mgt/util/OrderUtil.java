package com.ally.order.mgt.util;

import java.text.DecimalFormat;
import java.util.List;

import com.ally.order.mgt.domain.Product;

public class OrderUtil {

	public static String calculateTotalDueAmount(List<Product> products) {
		double totalDueAmount = 0.00;
		if(null != products && !products.isEmpty()) {
			totalDueAmount = products.stream()
					.mapToDouble(p -> (Double.valueOf(p.getPrice()) * Integer.valueOf(p.getQuantity()))).sum();
		}
		return String.valueOf(new DecimalFormat("0.00").format(totalDueAmount));
	}

}
