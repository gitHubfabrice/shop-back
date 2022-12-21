package com.fatechnologies.security.sidebar;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class SidebarDto {

    private Long id;
    private String label;
    private String icon;
    private String router;
    private int orderShow;
    private String description;
    private Set<SidebarDto> childs;

    public SidebarDto(Long id) {
        this.id = id;
        this.childs = new HashSet<>();
    }

    public SidebarDto(){
        this.childs = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public int getOrderShow() {
        return orderShow;
    }

    public void setOrderShow(int orderShow) {
        this.orderShow = orderShow;
    }

    public String getDescription() {
        return description;
    }

    public Set<SidebarDto> getChilds() {
        return childs;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * Comparator pour le tri du menu en fonction de orderShow
     */
    public static Comparator<SidebarDto> ComparatorOrder = new Comparator<SidebarDto>() {

        @Override
        public int compare(SidebarDto e1, SidebarDto e2) {
            return e1.getOrderShow() - (e2.getOrderShow());
        }
    };
}
