package kr.co.aike.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.dao.PrdImgDao;
import kr.co.aike.dao.ProductsDao;
import kr.co.aike.dao.UsersDao;
import kr.co.aike.domain.PrdImg;
import kr.co.aike.domain.Products;
import kr.co.aike.domain.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductsServiceImpl implements ProductsService {
	private final ProductsDao prdDao;
	private final PrdImgDao prdImgDao;
	
	public ModelAndView addMessages(int code, String imgText, String msg1Text, String msg2Text, String link1Text, String link1Href , String link2Text, String link2Href) {
		ModelAndView mav=new ModelAndView();
		String msg1="<h1 class=\"mb-4\">"+msg1Text+"</h1>";
		String img=imgText;
		String msg2="<h1 class=\"mb-4\">"+msg2Text+"</h1>";
		String link1="<input class=\"btn btn-dark rounded-pill py-3 px-5\" type='button' value='"+link1Text+"' onclick='"+link1Href+"'>&nbsp;&nbsp;";
		String link2="<input class=\"btn btn-outline-dark rounded-pill py-3 px-5\" type='button' value='"+link2Text+"' onclick='"+link2Href+"'>";
		if(code==0) {
			mav.addObject("msg2", msg2);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else if(code==1) {
			mav.addObject("msg2", msg2);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
		} else if(code==2) {
			mav.addObject("msg1", msg1);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		}
		return mav;
	}
	
	public ArrayList parsing(String str, String token) {
		StringTokenizer st1 = new StringTokenizer(str, token);
		ArrayList<String> pstr = new ArrayList<String>();
		while(st1.hasMoreTokens()){
		    pstr.add(st1.nextToken());
		}
		System.out.println(pstr);
		return pstr;
	}
	
	public int fileSave(String basePath, MultipartFile multipartFile) {
		// input form's parameter name
        String fileName = "";
        // original file name
        String originalFileName = multipartFile.getOriginalFilename();
        // file content type
        String contentType = multipartFile.getContentType();
        // file size
        long fileSize = multipartFile.getSize();

        System.out.println("fileSize: " + fileSize);
        System.out.println("originalFileName: " + originalFileName);

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            if( fileSize > 0 ) { // 파일이 존재한다면
                // 인풋 스트림을 얻는다.
                inputStream = multipartFile.getInputStream();
                File oldfile = new File(basePath, originalFileName);
                if ( oldfile.exists()){
                    for(int k=0; true; k++){
                        //파일명 중복을 피하기 위한 일련 번호를 생성하여
                        //파일명으로 조합
                        oldfile = new File(basePath,"("+k+")"+originalFileName);
                        //조합된 파일명이 존재하지 않는다면, 일련번호가
                        //붙은 파일명 다시 생성
                        if(!oldfile.exists()){ //존재하지 않는 경우
                            fileName = "("+k+")"+originalFileName;
                            break;
                        }//if end
                    }//for end
                }else{
                    fileName = originalFileName;
                }//if end

                //make server full path to save
                String serverFullPath = basePath + "\\" + fileName;
                //ec2 서버
                //String serverFullPath = basePath + "/" + fileName;

                System.out.println("fileName: " + fileName);
                System.out.println("serverFullPath: " + serverFullPath);

                outputStream = new FileOutputStream( serverFullPath );

                // 버퍼를 만든다.
                int readBytes = 0;
                byte[] buffer = new byte[8192];

                while((readBytes = inputStream.read(buffer, 0, 8192)) != -1 ) {
                    outputStream.write( buffer, 0, readBytes );
                }//while end

                outputStream.close();
                inputStream.close();

            }//if end
            return 1;

        }catch(Exception e) {
            e.printStackTrace();  
            return 0;
        }
		
	}
	
	public int saveImg(Products registerdProduct, String basePath, MultipartFile[] multipartFiles,String location) throws Exception {
		int cnt=0;
		
		PrdImg prdimg=new PrdImg();
		int filecnt=0;
		MultipartFile multipartFile=null;
		for(int x=0;x<multipartFiles.length;++x) {
			multipartFile = multipartFiles[x];
			cnt=fileSave(basePath,multipartFile);
			if(cnt==1) {
				//저장된 파일 db 저장
				prdimg.setPrdNo(registerdProduct.getPrdNo());
				prdimg.setFileName(multipartFile.getOriginalFilename());
				prdimg.setLocation(location);
				prdImgDao.insertProductImage(prdimg);
				
			}
			System.out.println(multipartFile.toString());
		}
		return cnt;
	}
	
	@Override
	public ModelAndView addProducts(Products products, HttpServletRequest request, @RequestParam("thumbnailimg") MultipartFile[] file1, @RequestParam("contentimg") MultipartFile[] file2) throws Exception {
		ModelAndView mav=new ModelAndView();
		System.out.println(products);
		
		//상품 등록
		ArrayList arr1= parsing(products.getColor(), ",");
		ArrayList arr2= parsing(products.getSize(), ",");
		
		int cnt=1;
				
		for(int y=0;y<arr1.size();++y) {
			products.setColor((String) arr1.get(y));
			for(int x=0;x<arr2.size();++x) {
				products.setSize((String) arr2.get(x));
				cnt = prdDao.insertProducts(products);
				if(cnt==1) continue;
				else if(cnt==0) cnt=0;
			}
		}
		
		System.out.println(request.getParameter("thumbnailimg"));
		
		//제일 먼저 등록된 상품을 대표 상품이라고 가정.
		products.setColor((String) arr1.get(0));
		products.setSize((String) arr2.get(0));
		Products registerdProduct = prdDao.selectProduct(products);
		System.out.println("results:"+registerdProduct);
		//이미지 등록
		String basePath = request.getRealPath("/storage");
		
		//섬네일 파일 이미지 저장 및 등록
		cnt = saveImg(registerdProduct, basePath, file1, "upper");
		//본문 파일 이미지 저장 및 등록
		cnt = saveImg(registerdProduct, basePath, file2, "lower");
		
		if(cnt==0){
			mav=addMessages(0,"<div class=\"mb-3\"><i class=\"bi bi-exclamation-triangle display-1 text-primary\"></i></div>","","!! 상품 등록 실패 !!","다시시도","javascript:history.back()","메인으로","location.href=\"../home\"");
		}else {
			mav=addMessages(1,"<div class=\"mb-3\"><i class=\"bi bi-stars text-warning\" style=\"font-size: 80px;\"></i></div>","","* ~ 상품 등록 성공 ~ *","상풍 등록하기로 이동","location.href=\"./add\"","메인으로","location.href=\"../home\"");
		}//if end
		mav.setViewName("products/msgView");
		return mav;
	}
	
	public ModelAndView getProductInfo(Products products) throws Exception {
		ModelAndView mav = new ModelAndView();
		//상품 정보 가져오기
		Products foundproduct=prdDao.selectProductPrdNo(products);
		mav.addObject("product",foundproduct);
		System.out.println(foundproduct.toString());
		
		//상품 이미지 가져오기
			//섬네일 이미지
		List<PrdImg> upperImages = prdImgDao.selectImagesUpper(foundproduct);
			//본문 이미지
		List<PrdImg> lowerImages = prdImgDao.selectImagesLower(foundproduct);
		mav.addObject("upperImages",upperImages);
		mav.addObject("lowerImages",lowerImages);
		
		return mav;
	}
	
	@Override
	public ModelAndView productDetail(Products products) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = getProductInfo(products);
		
		//상품 옵션 가져오기
		Products foundproduct=prdDao.selectProductPrdNo(products);
		List<String> color = prdDao.selectProductColor(foundproduct);
		List<String> size = prdDao.selectProductSize(foundproduct);
		mav.addObject("color",color);
		mav.addObject("size",size);
		
		mav.setViewName("products/detail");
		return mav;
	}
	
	@Override
	public ModelAndView preModifyProduct(@ModelAttribute Products products) throws Exception {
		ModelAndView mav=new ModelAndView();
		
		mav = getProductInfo(products);
		
		Products foundproduct = prdDao.selectProductPrdNo(products);
		
		//같은 디자인의 상품 번호 리스트 가져오기
		List<String> numList = prdDao.selectPrdNumList(foundproduct);
		System.out.println(numList);
		mav.addObject("numList", numList);
		
		//상품 옵션 가져오기
		List<String> color = prdDao.selectProductColor(foundproduct);
		List<String> size = prdDao.selectProductSize(foundproduct);
		String colorList="";
		for(int x=0;x<color.size();++x) {
			if(x==color.size()-1) {
				colorList+=color.get(x);
				break;
			}
			colorList+=color.get(x)+",";
		}
		System.out.println(colorList);
		String sizeList="";
		for(int x=0;x<size.size();++x) {
			if(x==size.size()-1) {
				sizeList+=size.get(x);
				break;
			}
			sizeList+=size.get(x)+",";
		}
		System.out.println(sizeList);
		mav.addObject("colorList",colorList);
		mav.addObject("sizeList",sizeList);
		
		mav.setViewName("products/editproduct");
		return mav;		
	}
	
	public void deleteImages(String basePath, String filename, String imgNo) {
		String pathname = basePath+"\\"+filename;
		File file = new File(pathname);
		if(file.exists()) {
			if (file.delete()) {
                System.out.println(filename+" 파일이 삭제 되었습니다~");
            } else {
                System.out.println(filename+" 파일 삭제 실패!!");
            }
		}
		
		//기존 이미지 파일 db삭제
		prdImgDao.deleteImg(imgNo);
		
	}
	
	public int modifyImages(Products registerdProduct, String[] imgNoList, MultipartFile[] fileList, String basePath, String location) throws Exception {
		int cnt=0;
		String[] previousImg=imgNoList;
		
		PrdImg prdimg=new PrdImg();
		MultipartFile multipartFile=null;
		
		for(int x=0;x<fileList.length;++x) {
			multipartFile = fileList[x];
			if(!multipartFile.isEmpty()) {
				//기존 이미지 파일 삭제
				System.out.println(basePath);
					//파일명 가져오기
				String filename = prdImgDao.getImgFileName(previousImg[x]);
				deleteImages(basePath, filename, previousImg[x]);
				
				//새로운 이미지 파일 db 저장
				prdimg.setPrdNo(registerdProduct.getPrdNo());
				prdimg.setFileName(multipartFile.getOriginalFilename());
				prdimg.setLocation(location);
				prdImgDao.insertProductImage(prdimg);
				
				//새로운 첨부 파일 저장
				cnt=fileSave(basePath,multipartFile);
			}else {
				//기존 이미지 파일 db의 제품번호를 바꿈
				prdImgDao.updatePrdNo(previousImg[x], registerdProduct.getPrdNo());
			}
		}
		
		return cnt;
	}
	
	@Override
	public ModelAndView modifyProduct(Products products, HttpServletRequest request, @RequestParam("prdNum") String[] prdNumList, @RequestParam("existingUpperImgNo") String[] upperImgNoList, @RequestParam("existingLowerImgNo") String[] lowerImgNoList, @RequestParam("thumbnailimg") MultipartFile[] file1, @RequestParam("contentimg") MultipartFile[] file2) throws Exception {
		ModelAndView mav=new ModelAndView();
		System.out.println(products);
		
		String[] prdNum=prdNumList;
		for(int x=0;x<prdNum.length;++x) {
			//기존 등록된 상품 삭제
			prdDao.deleteProduct(Long.parseLong(prdNum[x]));
		}
		
		//상품 새로 등록
		ArrayList arr1= parsing(products.getColor(), ",");
		ArrayList arr2= parsing(products.getSize(), ",");
		
		int cnt=1;
				
		for(int y=0;y<arr1.size();++y) {
			products.setColor((String) arr1.get(y));
			for(int x=0;x<arr2.size();++x) {
				products.setSize((String) arr2.get(x));
				cnt = prdDao.insertProducts(products);
				if(cnt==1) continue;
				else if(cnt==0) cnt=0;
			}
		}
				
		//제일 먼저 등록된 상품을 대표 상품이라고 가정.
		products.setColor((String) arr1.get(0));
		products.setSize((String) arr2.get(0));
		Products registerdProduct = prdDao.selectProduct(products);
		System.out.println("results:"+registerdProduct);
		
		//이미지 등록 수정
			//첨부한 파일 리스트를 확인해서
			//새로운 파일 리스트에 파일 있음 -> 기존 이미지 파일 db삭제하고 새로 등록함
			//새로운 파일 리스트에 파일 없음 -> 기존 이미지 파일 db의 제품번호를 바꿈
		String basePath = request.getRealPath("/storage");
	
		//섬네일 파일 관련 코드
		cnt = modifyImages(registerdProduct, upperImgNoList, file1, basePath, "upper");
		//본문 파일 관련 코드
		cnt = modifyImages(registerdProduct, lowerImgNoList, file2, basePath, "lower");
		
		if(cnt==0){
			mav=addMessages(0,"<div class=\"mb-3\"><i class=\"bi bi-exclamation-triangle display-1 text-primary\"></i></div>","","!! 상품 수정 실패 !!","다시시도","javascript:history.back()","메인으로","location.href=\"../home\"");
		}else {
			mav=addMessages(1,"<div class=\"mb-3\"><i class=\"bi bi-stars text-warning\" style=\"font-size: 80px;\"></i></div>","","* ~ 상품 수정 성공 ~ *","메인으로","location.href=\"../home\"", "", "");
		}//if end
		mav.setViewName("products/msgView");
		return mav;
	}
	
	@Override
	public ModelAndView preDeleteProduct(@ModelAttribute Products products) throws Exception {
		ModelAndView mav=new ModelAndView();
		Long prdNo = products.getPrdNo();
		mav=addMessages(2,"","상품을 삭제하시겠습니까?","","상품 삭제하기","location.href=\"./delete?prdNo="+prdNo+"\"","메인으로","location.href=\"../home\"");
		mav.setViewName("products/msgView");
		return mav;
	}
	
	@Override
	public ModelAndView deleteProduct(@ModelAttribute Products products, HttpServletRequest request) throws Exception {
		ModelAndView mav=new ModelAndView();
		int cnt=0;
		
		System.out.println(products.toString());
		
		//상품 이미지 가져오기
			//섬네일 이미지
		List<PrdImg> upperImages = prdImgDao.selectImagesUpper(products);
			//본문 이미지
		List<PrdImg> lowerImages = prdImgDao.selectImagesLower(products);
		
		String basePath = request.getRealPath("/storage");
		
		//상품 이미지 삭제
		PrdImg prdImg = null;
			//섬네일 이미지
		for(int x=0;x<upperImages.size();++x) {
			prdImg = upperImages.get(x);
			deleteImages(basePath, prdImg.getFileName(), String.valueOf(prdImg.getPrdImgNo()));
		}
			//본문 이미지
		for(int x=0;x<lowerImages.size();++x) {
			prdImg = lowerImages.get(x);
			deleteImages(basePath, prdImg.getFileName(), String.valueOf(prdImg.getPrdImgNo()));
		}		
		
		//상품명과 같은 상품의 상품번호를 다 가져옴
		List<Long> prdNoList = prdDao.selectPrdNumListAsNum(products.getPrdNo());
		//상품번호 조회한 것으로 상품 정보 다 삭제
		for(int x=0;x<prdNoList.size();++x) {
			cnt = prdDao.deleteProduct(prdNoList.get(x));
		}
		
		if(cnt==0){
			mav=addMessages(0,"<div class=\"mb-3\"><i class=\"bi bi-exclamation-triangle display-1 text-primary\"></i></div>","","!! 상품 삭제 실패 !!","다시시도","javascript:history.back()","메인으로","location.href=\"../home\"");
		}else {
			mav=addMessages(1,"<div class=\"mb-3\"><i class=\"bi bi-stars text-warning\" style=\"font-size: 80px;\"></i></div>","","* ~ 상품 삭제 성공 ~ *","메인으로","location.href=\"../home\"", "", "");
		}//if end
		mav.setViewName("products/msgView");
		return mav;
	}
	
	public String headPhraseSort1(Products products) {
		ModelAndView mav=new ModelAndView();
		String temp1=products.getSort1();
		String headPhrase="";
		if(temp1.equals("m")) {
			headPhrase+="남성";
			headPhrase+=headPhraseSort2(products);
		}else if(temp1.equals("w")) {
			headPhrase+="여성";
			headPhrase+=headPhraseSort2(products);
		}else if(temp1.equals("k")) {
			headPhrase+="키즈";
			headPhrase+=headPhraseSort2(products);
		}
		
		return headPhrase;
	}
	
	public String headPhraseSort2(Products products) {
		String temp2=products.getSort2();
		
		if(temp2.equals("sho")) {
			return "신발";
		}else if(temp2.equals("clo")) {
			return "의류";
		}else if(temp2.equals("acc")) {
			return "용품";
		}
		return null;
	}
	
	public ModelAndView pagingAsSort(Products products,String pageNum) throws Exception {
		ModelAndView mav=new ModelAndView();
		
		int totalRowCount=prdDao.totalRowCountAsSort(products); //총 상품 갯수 - 37개라고 가정
				
		//페이징
		int numPerPage = 6; //한 페이지당 레코드 갯수
		int pagePerBlock = 10; //페이지 리스트
		
		//String pageNum=req.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		
		//현재 페이지가 2페이지라고 하면, //12페이지라고 하면,
		int currentPage =Integer.parseInt(pageNum);		// 2	//12
		int startRow    =(currentPage-1)*numPerPage;  // 6 = (2-1)*5+1	//56=(12-1)*5+1
		int endRow      =currentPage*numPerPage;		// 10 = 2*5		//60=12*5
		
		//페이지 수
		double totcnt =(double)totalRowCount/numPerPage; // 7.4=37줄/5개씩
		int totalPage =(int)Math.ceil(totcnt);			 // 8 실제로 8페이지 끝....
		
		//현재 페이지가 2페이지라고 하면, 
		double d_page =(double)currentPage/pagePerBlock; //1.2 = 12/10		//1.2 = 12/10
		int Pages     =(int)Math.ceil(d_page)-1; // 0 = 1-1					//1 = 2-1
		int startPage =Pages*pagePerBlock+1;		 // 1 = 0*10+1			//11 = 1*10+1
		int endPage   =startPage+pagePerBlock-1; // 10 =  0 + 10 - 1		//20 = 11+10-1
		
		List<Products> list=null;
		List<PrdImg> imgList=new ArrayList<PrdImg>();
		PrdImg temp=null;
		if(totalRowCount>0) {
			list=prdDao.listAsSort(products, startRow, numPerPage);
			for(int x=0;x<list.size();++x) {
				System.out.println(list.get(x).toString());
				temp = prdImgDao.selectListImgPrdNo(list.get(x));
				System.out.println(temp);
				imgList.add(temp);
			}
		}else {
			list=Collections.EMPTY_LIST;
		}//if end

		mav.addObject("pageNum", currentPage);
		mav.addObject("count", totalRowCount);
		mav.addObject("totalPage", totalPage);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("list", list);
		mav.addObject("imgList", imgList);
		
		return mav;
		
	}
	
	@Override
	public ModelAndView list(@ModelAttribute Products products) throws Exception {
		ModelAndView mav=new ModelAndView();
		System.out.println(products.toString());
		mav = pagingAsSort(products, "1");
		//분류 문구 설정
		String headPhrase = headPhraseSort1(products);
			//해당 카테고리의 제품들 모두 검색하고 갯수 리턴
		int productCount = prdDao.totalRowCountAsSort(products);
		headPhrase+="("+productCount+")";
		mav.addObject("productCount",productCount);
		mav.addObject("headPhrase",headPhrase);
		
		mav.setViewName("products/list");
		return mav;
	}
	
	@Override
	public HashMap<String, List<?>> addList(@RequestParam Map<String, Object> map) throws Exception {
		List<Products> list = new ArrayList();
		List<PrdImg> imgList=new ArrayList<PrdImg>();
		
		System.out.println((String)map.get("cnt"));
        System.out.println((String)map.get("sort1"));
        System.out.println((String)map.get("sort2"));
        
        int cnt=Integer.valueOf((String) map.get("cnt"))-1; //Object여서 형 변환
        Products products = new Products();
        products.setSort1((String)map.get("sort1"));
        products.setSort2((String)map.get("sort2"));
        
        int numPerPage = 6; //한 페이지당 레코드 갯수
		list=prdDao.listAsSort(products, cnt, numPerPage);
		PrdImg temp=null;
		for(int x=0;x<list.size();++x) {
			System.out.println(list.get(x).toString());
			temp = prdImgDao.selectListImgPrdNo(list.get(x));
			System.out.println(temp);
			imgList.add(temp);
		}			
		System.out.println(list.toString());
		System.out.println(imgList.toString());
		
		HashMap<String, List<?>> responseMap = new HashMap<>();
		responseMap.put("product", list);
		responseMap.put("img", imgList);
		return responseMap;
	}

}
