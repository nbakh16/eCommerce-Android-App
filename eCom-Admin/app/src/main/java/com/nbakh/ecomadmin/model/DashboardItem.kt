package com.nbakh.ecomadmin.model

import com.nbakh.ecomadmin.R

data class DashboardItem(
    val icon: Int,
    val title: String
)

val dashboardItemList = listOf<DashboardItem>(
    DashboardItem(R.drawable.ic_baseline_add_24, "Add Product"),
    DashboardItem(R.drawable.ic_baseline_list_24, "View Product"),
    DashboardItem(R.drawable.ic_baseline_orders_24, "Orders"),
    DashboardItem(R.drawable.ic_baseline_category_24, "Category"),
    DashboardItem(R.drawable.ic_baseline_users_24, "Users"),
    DashboardItem(R.drawable.ic_baseline_report_24, "Report")
)