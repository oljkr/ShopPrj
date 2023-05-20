package kr.co.aike.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
		
		//섬네일 파일 관련 코드 <input type='file' name='thumbnailimg'>
		MultipartFile[] thumbnails=file1;
		
		PrdImg prdimg=new PrdImg();
		int filecnt=0;
		MultipartFile multipartFile1=null;
		for(int x=0;x<thumbnails.length;++x) {
			multipartFile1 = thumbnails[x];
			cnt=fileSave(basePath,multipartFile1);
			if(cnt==1) {
				//저장된 파일 db 저장
				prdimg.setPrdNo(registerdProduct.getPrdNo());
				prdimg.setFileName(multipartFile1.getOriginalFilename());
				prdimg.setLocation("upper");
				prdImgDao.insertProductImage(prdimg);
				
			}
			System.out.println(multipartFile1.toString());
		}
		
		//본문 파일 관련 코드 <input type='file' name='contentimg'>
		MultipartFile[] contentImgs=file2;
		
		MultipartFile multipartFile2=null;
		for(int x=0;x<contentImgs.length;++x) {
			multipartFile2 = contentImgs[x];
			cnt=fileSave(basePath,multipartFile2);
			if(cnt==1) {
				//저장된 파일 db 저장
				prdimg.setPrdNo(registerdProduct.getPrdNo());
				prdimg.setFileName(multipartFile2.getOriginalFilename());
				prdimg.setLocation("lower");
				prdImgDao.insertProductImage(prdimg);
			}
			System.out.println(multipartFile2.toString());
		}
		
		if(cnt==0){
			mav=addMessages(0,"<div class=\"mb-3\"><i class=\"bi bi-exclamation-triangle display-1 text-primary\"></i></div>","","!! 상품 등록 실패 !!","다시시도","javascript:history.back()","메인으로","location.href=\"../home\"");
		}else {
			mav=addMessages(1,"<div class=\"mb-3\"><i class=\"bi bi-stars text-warning\" style=\"font-size: 80px;\"></i></div>","","* ~ 상품 등록 성공 ~ *","상풍 등록하기로 이동","location.href=\"./add\"","메인으로","location.href=\"../home\"");
		}//if end
		mav.setViewName("products/msgView");
		return mav;
	}
	
	@Override
	public ModelAndView productDetail(Products products) throws Exception {
		ModelAndView mav = new ModelAndView();
		Products foundproduct = new Products();
		//상품 정보 가져오기
		foundproduct=prdDao.selectProductPrdNo(products);
		mav.addObject("product",foundproduct);
		System.out.println(foundproduct.toString());
		
		//상품 옵션 가져오기
		List<String> color = prdDao.selectProductColor(foundproduct);
		List<String> size = prdDao.selectProductSize(foundproduct);
		mav.addObject("color",color);
		mav.addObject("size",size);
		
		//상품 이미지 가져오기
			//섬네일 이미지
		List<PrdImg> upperImages = prdImgDao.selectImagesUpper(foundproduct);
			//본문 이미지
		List<PrdImg> lowerImages = prdImgDao.selectImagesLower(foundproduct);
		mav.addObject("upperImages",upperImages);
		mav.addObject("lowerImages",lowerImages);
		
		mav.setViewName("products/detail");
		return mav;
	}
	
	@Override
	public ModelAndView preModifyProduct(@ModelAttribute Products products) throws Exception {
		ModelAndView mav=new ModelAndView();
		log.info(products.toString());
		
		Products foundproduct = new Products();
		//상품 정보 가져오기
		foundproduct=prdDao.selectProductPrdNo(products);
		mav.addObject("product", foundproduct);
		System.out.println(foundproduct.toString());
		
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
		
		//상품 이미지 가져오기
			//섬네일 이미지
		List<PrdImg> upperImages = prdImgDao.selectImagesUpper(foundproduct);
			//본문 이미지
		List<PrdImg> lowerImages = prdImgDao.selectImagesLower(foundproduct);
		mav.addObject("upperImages",upperImages);
		mav.addObject("lowerImages",lowerImages);
	
		mav.setViewName("products/editproduct");
		return mav;		
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
		String[] previousImg=upperImgNoList;
		MultipartFile[] fileList=file1;
		
		PrdImg prdimg=new PrdImg();
		MultipartFile multipartFile=null;
		
		for(int x=0;x<fileList.length;++x) {
			multipartFile = fileList[x];
			if(!multipartFile.isEmpty()) {
				//기존 이미지 파일 삭제
				System.out.println(basePath);
					//파일명 가져오기
				String filename = prdImgDao.getImgFileName(previousImg[x]);
				String pathname = basePath+"\\"+filename;
				File file = new File(pathname);
				if(file.exists()) {
					if (file.delete()) {
	                    System.out.println("파일이 삭제 되었습니다~");
	                } else {
	                    System.out.println("파일 삭제 실패!!");
	                }
				}
				
				//기존 이미지 파일 db삭제
				prdImgDao.deleteImg(previousImg[x]);
				prdimg.setPrdNo(registerdProduct.getPrdNo());
				prdimg.setFileName(multipartFile.getOriginalFilename());
				prdimg.setLocation("upper");
				prdImgDao.insertProductImage(prdimg);
				
				//새로운 첨부 파일 저장
				cnt=fileSave(basePath,multipartFile);
			}else {
				//기존 이미지 파일 db의 제품번호를 바꿈
				prdImgDao.updatePrdNo(previousImg[x], registerdProduct.getPrdNo());
			}
		}
		
		//본문 파일 관련 코드
		previousImg=lowerImgNoList;
		fileList=file2;
		
		for(int x=0;x<fileList.length;++x) {
			multipartFile = fileList[x];
			if(!multipartFile.isEmpty()) {
				//파일 저장
				cnt=fileSave(basePath,multipartFile);
				
				//기존 이미지 파일 db삭제
				prdImgDao.deleteImg(previousImg[x]);
				prdimg.setPrdNo(registerdProduct.getPrdNo());
				prdimg.setFileName(multipartFile.getOriginalFilename());
				prdimg.setLocation("lower");
				prdImgDao.insertProductImage(prdimg);
			}else {
				//기존 이미지 파일 db의 제품번호를 바꿈
				prdImgDao.updatePrdNo(previousImg[x], registerdProduct.getPrdNo());
			}
		}
		
		if(cnt==0){
			mav=addMessages(0,"<div class=\"mb-3\"><i class=\"bi bi-exclamation-triangle display-1 text-primary\"></i></div>","","!! 상품 수정 실패 !!","다시시도","javascript:history.back()","메인으로","location.href=\"../home\"");
		}else {
			mav=addMessages(1,"<div class=\"mb-3\"><i class=\"bi bi-stars text-warning\" style=\"font-size: 80px;\"></i></div>","","* ~ 상품 수정 성공 ~ *","메인으로","location.href=\"../home\"", "", "");
		}//if end
		mav.setViewName("products/msgView");
		return mav;
	}

}
