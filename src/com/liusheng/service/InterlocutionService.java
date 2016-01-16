package com.liusheng.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.liusheng.dao.InterlocutionDao;
import com.liusheng.entities.Interlocution;

@Service
public class InterlocutionService {

	@Autowired
	private InterlocutionDao iDao;

	public boolean addOneInterlocution(Interlocution il, MultipartFile mf,ServletContext context) {

		// 获取上传路径baseUrl
		String baseUrl = context.getRealPath("") + "\\file\\";

		if (null != mf) {
			String imgUrl = "";
			String fileName = mf.getOriginalFilename();
			if (!"".equalsIgnoreCase(fileName)) {
				// 传到服务器文件夹中的文件名称为 ctimeMillis+3个随机整数+原始文件名.suffix
				Long currentTimeMillis = System.currentTimeMillis();
				StringBuilder sb = new StringBuilder(
						currentTimeMillis.toString());
				for (int i = 0; i < 3; i++) {
					sb.append((int) (Math.random() * 10));
				}

				// 获取后缀
				String suffix = fileName.substring(fileName.indexOf("."),
						fileName.length());

				sb.append(suffix);
				if (fileName.endsWith(".png") || fileName.endsWith(".jpg")
						|| fileName.endsWith(".gif")
						|| fileName.endsWith(".jpeg")) {

					// 上传的是图片
					imgUrl = "uploadfile/" + sb;

				}
				try {
					mf.transferTo(new File(baseUrl + sb));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			il.setImgUrl(imgUrl);
			return iDao.addOneInterlocution(il);
		}

		return false;
	}

	public boolean deleteOneInterlocution(int id) {
		return iDao.deleteOneInterlocution(id);
	}

	public void updataOneInterlocution(Interlocution ss) {
	}

	public Interlocution getOneInterlocution(int id) {
		return null;
	}

	public List<Interlocution> getAllInterlocution(int page, int itemNums,int state) {
		int start = (page-1)*itemNums;
		return iDao.getAllInterlocution(start, itemNums,state);
	}

	public int geteInterLocutionPageNums(int items){
		double count = iDao.getInterlocaionCount();
		int pages = (int) Math.ceil(count/items);
		return pages;
	}
	
	public boolean checkOneInterlocution(int id) {
		return iDao.checkOneInterlocution(id);
	}

	public List<Interlocution> createInter(Map<String, Integer> map) {
		/**
		 * 1.查询知识点1 kid 的集合 daoImpl里面写的 2.从中随机选择一定数量的题目
		 * 
		 * */
		List<Interlocution> lists = new ArrayList<Interlocution>();
		List<Interlocution> list = null;
		for (Map.Entry<String, Integer> m : map.entrySet()) {
			String kpId = m.getKey();
			// 该知识点要出的数量
			Integer nums = m.getValue();
			list = iDao.createInterlocaionByKid(kpId, nums);
			lists.addAll(list);

		}
		return lists;
	}
}
