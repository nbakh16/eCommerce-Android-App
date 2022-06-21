package com.nbakh.ecomadmin.model

import com.nbakh.ecomadmin.R

data class DashboardItem(
    val icon: Int,
    val title: String,
    val type: DashboardItemType
)

enum class DashboardItemType {
    ADD_PRODUCT, VIEW_PRODUCT, CATEGORY, ORDER, USER, REPORT, SETTINGS, LOGOUT
}

val dashboardItemList = listOf<DashboardItem>(
    DashboardItem(R.drawable.ic_baseline_add_24, "Add Product", DashboardItemType.ADD_PRODUCT),
    DashboardItem(R.drawable.ic_baseline_list_24, "View Product", DashboardItemType.VIEW_PRODUCT),
    DashboardItem(R.drawable.ic_baseline_category_24, "Category", DashboardItemType.CATEGORY),
    DashboardItem(R.drawable.ic_baseline_orders_24, "Orders", DashboardItemType.ORDER),
    DashboardItem(R.drawable.ic_baseline_users_24, "Users", DashboardItemType.USER),
    DashboardItem(R.drawable.ic_baseline_report_24, "Report", DashboardItemType.REPORT),
    DashboardItem(R.drawable.ic_baseline_settings_24, "Settings", DashboardItemType.SETTINGS),
    DashboardItem(R.drawable.ic_baseline_logout_24, "Logout", DashboardItemType.LOGOUT)
)
