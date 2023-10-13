package com.example.module4_shoesshop.Controller;
import com.example.module4_shoesshop.Service.IService.*;
import com.example.module4_shoesshop.model.*;
import com.example.module4_shoesshop.model.dto.CustomerInfoDTO;
import com.example.module4_shoesshop.model.dto.OrderDataDTO;
import com.example.module4_shoesshop.model.dto.ProductPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IProductService iProductService;

    @Autowired
    IOderService iOderService;

    @Autowired
    IOderDetailService iOderDetailService;

    @Autowired
    IAccountService iAccountService;

    @Autowired
    ISizeService iSizeService;

    @Autowired
    ICustomerInfoService iCustomerInfoService;

    @Value("${upload.image.path}")
    private String fileUpload;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> ProductList = iProductService.getAll();
        if (ProductList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ProductList, HttpStatus.OK);
    }

    @GetMapping("/getPage")
    public ResponseEntity<ProductPagination> getProductPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                  @RequestParam(value = "limit", defaultValue = "3") int limit) {
        int productQuantity = iProductService.getAll().size();
        int totalPages = productQuantity < limit ? 1 : (int) Math.ceil((double) productQuantity / limit);
        List<Product> ProductList = iProductService.findProduct(page-1, limit);
        ProductPagination productPagination = new ProductPagination(totalPages,productQuantity , ProductList);
        if (ProductList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(productPagination, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestParam("file")  MultipartFile file,
                                              @RequestParam(value = "productName") String productName,
                                              @RequestParam(value = "price") double price,
                                              @RequestParam(value = "salePercentage") Double salePercentage,
                                              @RequestParam(value = "quantity") int quantity,
                                              @RequestParam(value = "color") String color,
                                              @RequestParam(value = "describle") String describle,
                                              @RequestParam(value = "category") Category category,
                                              @RequestParam(value = "brand") Brand brand) throws IOException {

        String fileName = file.getOriginalFilename();
        String filePath = fileUpload + "/" + fileName;
        File imageFile = new File(filePath);
        if (!imageFile.exists()) {
            file.transferTo(imageFile);
        }
        double salePrice = price - salePercentage*price;
        Product product = new Product();
        List<Size> sizeList = iSizeService.getAll();
        product.setSize(sizeList);
        product.setProductName(productName);
        product.setPrice(price);
        product.setSalePercentage(salePercentage);
        product.setSalePrice(salePrice);
        product.setQuantity(quantity);
        product.setColor(color);
        product.setDescrible(describle);
        product.setCategory(category);
        product.setBrand(brand);
        product.setImage(fileName);
        return new ResponseEntity<>(iProductService.save(product), HttpStatus.CREATED);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        Product currentProduct = iProductService.findById(id);
        if (currentProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentProduct, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Product> editProduct(@RequestBody Product Product, @PathVariable long id) {
        Product currentProduct = iProductService.findById(id);
        if (currentProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentProduct.setProductName(Product.getProductName());
        currentProduct.setPrice(Product.getPrice());
        currentProduct.setSalePercentage(Product.getSalePercentage());
        currentProduct.setSalePrice(Product.getSalePrice());
        currentProduct.setQuantity(Product.getQuantity());
        currentProduct.setColor(Product.getColor());
        currentProduct.setDescrible(Product.getDescrible());
        currentProduct.setImage(Product.getImage());
        currentProduct.setCategory(Product.getCategory());
        currentProduct.setBrand(Product.getBrand());
        return new ResponseEntity<>(iProductService.edit(currentProduct), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        if (iProductService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iProductService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findsize/{id}")
    public ResponseEntity<Integer> getNumberOfSizeFromProduct(@PathVariable int id) {
        Product currentProduct = iProductService.findById(id);
        if (currentProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentProduct.getSize().size(), HttpStatus.OK);
    }

    @GetMapping("/findByCateID/{id}")
    public ResponseEntity<Integer> findProductsQuantityByCategoryID(@PathVariable int id) {
        int productQuantity = iProductService.findByCateID(id);
        return new ResponseEntity<>(productQuantity, HttpStatus.OK);
    }

    @GetMapping("/findProductByCateID/{id}")
    public ResponseEntity<List<Product>> findProductsListByCategoryID(@PathVariable int id) {
        List<Product> productList = iProductService.findProductByCategoryID(id);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findByPrice/{r1}/{r2}") // Sử dụng / khi có nhiều tham số truyền về
    public ResponseEntity<List<Product>> findProductsByPriceID(@PathVariable int r1, @PathVariable int r2) {
        List<Product> productListByRange = iProductService.findProductByPriceRange(r1, r2);
        return new ResponseEntity<>(productListByRange, HttpStatus.OK);
    }

    @GetMapping("/findProductBySize/{id}")
    public ResponseEntity<List<Product>> findProductBySizeID(@PathVariable long id) {
        List<Product> ProductList = iProductService.findProductBySizeID(id);
        if (ProductList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ProductList, HttpStatus.OK);
    }

//    @PostMapping("/cart")
//    public ResponseEntity<?>createOderCart(@RequestBody Product[]products) {
//        String username="";
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication.isAuthenticated()) {
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            username = userDetails.getUsername();
//        }
//        Account account = iAccountService.findByUsername(username);
//
//
//        if(checkProductQuantity(products)){
//            Oder oder = new Oder();
//            oder.setAccount(account);
//            oder.setTotalPrice(totalPrice(products));
//            oder.setDate(LocalDate.now());
//            oder.setQuantity(getQuantity(products));
//            iOderService.save(oder);
//            for (Product p : products) {
//                OderDetail oderDetail = new OderDetail();
//                oderDetail.setOder(oder);
//                oderDetail.setSize(p.getSize().get(0).getSize());
//                oderDetail.setQuantity(p.getQuantity());
//                oderDetail.setPrice(p.getPrice());
//                oderDetail.setProduct(p);
//                iOderDetailService.save(oderDetail);
//                return new ResponseEntity<>(HttpStatus.OK);
//            }
//        }
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PostMapping("/cart")
    public ResponseEntity<?> createOderCart(@RequestBody OrderDataDTO orderDataDTO) {
        String username = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }
        Account account = iAccountService.findByUsername(username);
        Product[] products = orderDataDTO.getProducts();
        CustomerInfoDTO customerInfoDTO = orderDataDTO.getCustomerInfoDTO();
        if (checkProductQuantity(products)) {
            Oder oder = new Oder();
            oder.setAccount(account);
            oder.setTotalPrice(totalPrice(products));
            oder.setDate(LocalDateTime.now());
            oder.setQuantity(getQuantity(products));
            iOderService.save(oder);
            saveToCustomerInfo(customerInfoDTO, oder, account);
            for (Product p : products) {
                OderDetail oderDetail = new OderDetail();
                oderDetail.setOder(oder);
                oderDetail.setSize(p.getSize().get(0).getSize());
                oderDetail.setQuantity(p.getQuantity());
                oderDetail.setPrice(p.getPrice());
                oderDetail.setProduct(p);
                iOderDetailService.save(oderDetail);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public void saveToCustomerInfo(CustomerInfoDTO customer, Oder oder, Account account) {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAccount(account);
        customerInfo.setOder(oder);
        customerInfo.setFullname(customer.getFullname());
        customerInfo.setAddress(customer.getAddress());
        customerInfo.setCountry(customer.getCountry());
        customerInfo.setEmail(customer.getEmail());
        customerInfo.setPhoneNumber(customer.getPhoneNumber());
        customerInfo.setOrderNotes(customer.getOrderNotes());
        iCustomerInfoService.save(customerInfo);
    }

    public int getQuantity(Product[] products) {
        int quantity = 0;
        for (Product p : products) {
            quantity += p.getQuantity();
        }
        return quantity;
    }

    @GetMapping("/checkRemainProduct/{id}")
    public boolean checkRemainProduct(@PathVariable long id) {
        Product product = iProductService.findById(id);
        if (product.getQuantity() == 0) {
            return false;
        }
        return true;
    }

    public boolean checkProductQuantity(Product[] products) {
        boolean checkP = true;
        for (Product p : products) {
            Product product = getProduct(p.getId()).getBody();
            if (p.getQuantity() > product.getQuantity()) {
                checkP = false;
                return checkP;
            }
        }
        return checkP;
    }

    public double totalPrice(Product[] products) {
        double sum = 0;
        for (Product p : products) {
            sum += p.getPrice() * p.getQuantity();
        }
        return sum;
    }
}
