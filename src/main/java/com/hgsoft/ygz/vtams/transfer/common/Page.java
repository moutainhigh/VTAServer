package com.hgsoft.ygz.vtams.transfer.common;

import com.hgsoft.ygz.vtams.transfer.common.Order.Direction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 分页
 *
 * @author 赖少涯
 * @date 2017-10-16
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 内容 */
	private final List<T> content = new ArrayList<T>();

	/** 总记录数 */
	private final long total;

	/** 分页信息 */
	private final Pageable pageable;

	/**
	 * 初始化一个新创建的Page对象
	 */
	public Page() {
		this.total = 0L;
		this.pageable = new Pageable();
	}

	/**
	 * @param content
	 *            内容
	 * @param total
	 *            总记录数
	 * @param pageable
	 *            分页信息
	 */
	public Page(List<T> content, long total, Pageable pageable) {
		this.content.addAll(content);
		this.total = total;
		this.pageable = pageable;
	}

	/**
	 * 获取页码
	 * 
	 * @return 页码
	 */
	public int getPageNumber() {
		return pageable.getPageNumber();
	}

	/**
	 * 获取每页记录数
	 * 
	 * @return 每页记录数
	 */
	public int getPageSize() {
		return pageable.getPageSize();
	}

	/**
	 * 获取搜索属性
	 * 
	 * @return 搜索属性
	 */
	@JsonIgnore
	public String getSearchProperty() {
		return pageable.getSearchProperty();
	}

	/**
	 * 获取搜索值
	 * 
	 * @return 搜索值
	 */
	@JsonIgnore
	public String getSearchValue() {
		return pageable.getSearchValue();
	}

	/**
	 * 获取排序属性
	 * 
	 * @return 排序属性
	 */
	@JsonIgnore
	public String getOrderProperty() {
		return pageable.getOrderProperty();
	}

	/**
	 * 获取排序方向
	 * 
	 * @return 排序方向
	 */
	@JsonIgnore
	public Direction getOrderDirection() {
		return pageable.getOrderDirection();
	}

	/**
	 * 获取排序
	 * 
	 * @return 排序
	 */
	@JsonIgnore
	public List<Order> getOrders() {
		return pageable.getOrders();
	}

	/**
	 * 获取筛选
	 * 
	 * @return 筛选
	 */
	@JsonIgnore
	public List<Filter> getFilters() {
		return pageable.getFilters();
	}

	/**
	 * 获取总页数
	 * 
	 * @return 总页数
	 */
	public int getTotalPages() {
		return (int) Math.ceil((double) getTotal() / (double) getPageSize());
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	public List<T> getContent() {
		return content;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return 总记录数
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * 获取分页信息
	 * 
	 * @return 分页信息
	 */
	@JsonIgnore
	public Pageable getPageable() {
		return pageable;
	}

	/**
	 * 是否有上一页
	 * @return
	 */
	@JsonIgnore
	public boolean getHasPrevious() {
	    return pageable.getPageNumber() > 1;
	}
	/**
	 * 是否有下一页
	 * @return
	 */
	@JsonIgnore
	public boolean getHasNext() {
	    return pageable.getPageNumber() < getTotalPages();
	}
	/**
	 * 是否第一页
	 * @return
	 */
	@JsonIgnore
	public boolean getIsFirst() {
	    return pageable.getPageNumber() == 1;
	}
	/**
	 * 是否最后一页
	 * @return
	 */
	@JsonIgnore
	public boolean getIsLast() {
	    return getPageNumber() == getTotalPages();
	}
	/**
	 * 取得上一页页码
	 * @return
	 */
	@JsonIgnore
	public int getPreviousPageNumber() {
	    return pageable.getPageNumber() - 1;
	}
	/**
     * 取得下一页页码
     * @return
     */
	@JsonIgnore
	public int getNextPageNumber() {
	    return pageable.getPageNumber() + 1;
	}
	/**
     * 取得第一页页码
     * @return
     */
	@JsonIgnore
	public int getFirstPageNumber() {
	    return 1;
	}
	/**
     * 取得最后一页页码
     * @return
     */
	@JsonIgnore
	public int getLastPageNumber() {
	    return getTotalPages();
	}
	/**
	 * 取得段列
	 * @return
	 */
	@JsonIgnore
	public List<Integer> getSegment() {
	    int pageNumber = getPageNumber();
	    int segmentCount = 5;
	    int totalPages = getTotalPages();
	    
	    int startSegmentPageNumber = pageNumber - (int) Math.floor((segmentCount - 1) / 2D);
        int endSegmentPageNumber = pageNumber + (int) Math.ceil((segmentCount - 1) / 2D);
        if (startSegmentPageNumber < 1) {
            startSegmentPageNumber = 1;
        }
        if (endSegmentPageNumber > totalPages) {
            endSegmentPageNumber = totalPages;
        }
        List<Integer> segment = new ArrayList<Integer>();
        for (int i = startSegmentPageNumber; i <= endSegmentPageNumber; i++) {
            segment.add(i);
        }
        return segment;
	}
	
}