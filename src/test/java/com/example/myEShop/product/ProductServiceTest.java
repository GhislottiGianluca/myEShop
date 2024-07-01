package com.example.myEShop.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    @Mock
    ProductDTOMapper productDTOMapper;
    ProductService underTest;


    Product product1, product2, product3, product4;
    ProductDTO productDTO1, productDTO2, productDTO3, productDTO4;
    List<Product> products;

    @BeforeEach
    void setUp() {
        underTest = new ProductService(productRepository, productDTOMapper);

        // Initialize common test data
        product1 = new Product();
        product1.setId(1L);
        product2 = new Product();
        product2.setId(2L);
        product3 = new Product();
        product3.setId(3L);
        product4 = new Product();
        product4.setId(4L);

        productDTO1 = new ProductDTO(1L, "Title1", "Description1", "Image1", "Image2", "Image3", "Image4", 10.0, "CHF");
        productDTO2 = new ProductDTO(2L, "Title2", "Description2", "Image1", "Image2", "Image3", "Image4", 20.0, "CHF");
        productDTO3 = new ProductDTO(3L, "Title3", "Description3", "Image1", "Image2", "Image3", "Image4", 30.0, "CHF");
        productDTO4 = new ProductDTO(4L, "Title4", "Description4", "Image1", "Image2", "Image3", "Image4", 40.0, "CHF");

        products = Arrays.asList(product1, product2, product3, product4);
    }

    @Test
    void canGetProduct() {
        // Given
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        when(productDTOMapper.apply(product1)).thenReturn(productDTO1);
        when(productDTOMapper.apply(product2)).thenReturn(productDTO2);

        // When
        List<ProductDTO> result = underTest.getProduct();

        // Then
        List<ProductDTO> expected = Arrays.asList(productDTO1, productDTO2);
        assertEquals(expected, result);

        verify(productRepository).findAll();
        verify(productDTOMapper).apply(product1);
        verify(productDTOMapper).apply(product2);
    }

    @Test
    void canGetProductById() {
        // Given
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        Optional<Product> optionalProduct = Optional.of(product);

        when(productRepository.findProductById(id)).thenReturn(optionalProduct);

        when(productDTOMapper.apply(product)).thenReturn(productDTO1);

        // When
        ProductDTO result = underTest.getProductById(id);

        // Then
        assertEquals(productDTO1, result);

        verify(productRepository).findProductById(id);
        verify(productDTOMapper).apply(product);
    }

    @Test
    void canAddNewProduct() {
        // Given
        Product product = new Product();
        product.setId(1L);
        product.setTitle("New Product");
        product.setDescription("Description of the new product");

        // When
        underTest.addNewProduct(product);

        // Then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());

        Product capturedProduct = productArgumentCaptor.getValue();
        assertEquals(product, capturedProduct);
    }

    @Test
    void deleteProduct_whenProductExists() {
        // Given
        Long id = 1L;
        when(productRepository.existsById(id)).thenReturn(true);

        // When
        underTest.deleteProduct(id);

        // Then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(productRepository).existsById(idArgumentCaptor.capture());
        verify(productRepository).deleteById(idArgumentCaptor.capture());

        Long capturedId = idArgumentCaptor.getValue();
        assertEquals(id, capturedId);
    }

    @Test
    void deleteProduct_whenProductDoesNotExist() {
        // Given
        Long id = 1L;
        when(productRepository.existsById(id)).thenReturn(false);

        // When
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> underTest.deleteProduct(id));

        // Then
        assertEquals("Product with id: " + id + " does not exists.", exception.getMessage());

        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(productRepository).existsById(idArgumentCaptor.capture());
        verify(productRepository, never()).deleteById(anyLong());

        Long capturedId = idArgumentCaptor.getValue();
        assertEquals(id, capturedId);
    }

    @Test
    void updateProduct_whenTitleAndDescriptionAreUpdated() {
        // Given
        Long id = 1L;
        String newTitle = "New Title";
        String newDescription = "New Description";

        Product product = new Product();
        product.setId(id);
        product.setTitle("Old Title");
        product.setDescription("Old Description");

        when(productRepository.findProductById(id)).thenReturn(Optional.of(product));

        // When
        underTest.updateProduct(id, newTitle, newDescription);

        // Then
        verify(productRepository).findProductById(id);

        assertEquals(newTitle, product.getTitle());
        assertEquals(newDescription, product.getDescription());
    }

    @Test
    void updateProduct_whenOnlyTitleIsUpdated() {
        // Given
        Long id = 1L;
        String newTitle = "New Title";

        Product product = new Product();
        product.setId(id);
        product.setTitle("Old Title");
        product.setDescription("Old Description");

        when(productRepository.findProductById(id)).thenReturn(Optional.of(product));

        // When
        underTest.updateProduct(id, newTitle, null);

        // Then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).findProductById(id);

        assertEquals(newTitle, product.getTitle());
        assertEquals("Old Description", product.getDescription());
    }

    @Test
    void updateProduct_whenOnlyDescriptionIsUpdated() {
        // Given
        Long id = 1L;
        String newDescription = "New Description";

        Product product = new Product();
        product.setId(id);
        product.setTitle("Old Title");
        product.setDescription("Old Description");

        when(productRepository.findProductById(id)).thenReturn(Optional.of(product));

        // When
        underTest.updateProduct(id, null, newDescription);

        // Then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).findProductById(id);

        assertEquals("Old Title", product.getTitle());
        assertEquals(newDescription, product.getDescription());
    }

    @Test
    void canUpdateProductwhenProductDoesNotExist() {
        // Given
        Long id = 1L;
        String newTitle = "New Title";
        String newDescription = "New Description";

        when(productRepository.findProductById(id)).thenReturn(Optional.empty());

        // When
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> underTest.updateProduct(id, newTitle, newDescription));

        // Then
        assertEquals("Product with title: " + newTitle + " and ID: " + id + " does not exist", exception.getMessage());
        verify(productRepository).findProductById(id);
    }

    @Test
    void canGetProductsByPrice() {
        // Given
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.getProductsByPrice()).thenReturn(products);

        when(productDTOMapper.apply(product1)).thenReturn(productDTO1);
        when(productDTOMapper.apply(product2)).thenReturn(productDTO2);

        // When
        List<ProductDTO> result = underTest.getProductsByPrice();

        // Then
        List<ProductDTO> expected = Arrays.asList(productDTO1, productDTO2);
        assertEquals(expected, result);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).getProductsByPrice();
        verify(productDTOMapper, times(2)).apply(productArgumentCaptor.capture());
    }

    @Test
    void canGetProductsBySold() {
        // Given
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.getProductsBySold()).thenReturn(products);

        when(productDTOMapper.apply(product1)).thenReturn(productDTO1);
        when(productDTOMapper.apply(product2)).thenReturn(productDTO2);

        // When
        List<ProductDTO> result = underTest.getProductsBySold();

        // Then
        List<ProductDTO> expected = Arrays.asList(productDTO1, productDTO2);
        assertEquals(expected, result);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).getProductsBySold();
        verify(productDTOMapper, times(2)).apply(productArgumentCaptor.capture());
    }

    @Test
    void canGetBestFourProductsForSales() {

        when(productRepository.getBestFourProductForSales()).thenReturn(Arrays.asList(product1, product2, product3, product4));

        ProductDTO productDTO1 = new ProductDTO(1L, "Title1", "Description1", "Image1", "Image2", "Image3", "Image4", 10.0, "USD");
        ProductDTO productDTO2 = new ProductDTO(2L, "Title2", "Description2", "Image1", "Image2", "Image3", "Image4", 20.0, "USD");
        ProductDTO productDTO3 = new ProductDTO(3L, "Title3", "Description3", "Image1", "Image2", "Image3", "Image4", 30.0, "USD");
        ProductDTO productDTO4 = new ProductDTO(4L, "Title4", "Description4", "Image1", "Image2", "Image3", "Image4", 40.0, "USD");

        when(productDTOMapper.apply(product1)).thenReturn(productDTO1);
        when(productDTOMapper.apply(product2)).thenReturn(productDTO2);
        when(productDTOMapper.apply(product3)).thenReturn(productDTO3);
        when(productDTOMapper.apply(product4)).thenReturn(productDTO4);

        // When
        List<ProductDTO> result = underTest.getBestFourProductsForSales();

        // Then
        List<ProductDTO> expected = Arrays.asList(productDTO1, productDTO2, productDTO3, productDTO4);
        assertEquals(expected, result);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).getBestFourProductForSales();
        verify(productDTOMapper, times(4)).apply(productArgumentCaptor.capture());
    }


    @Test
    void canGetProductByCategory() {
        // Given
        String category = ProductCategories.CUP.toString();
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findProductsByCategory(category)).thenReturn(products);

        when(productDTOMapper.apply(product1)).thenReturn(productDTO1);
        when(productDTOMapper.apply(product2)).thenReturn(productDTO2);

        // When
        List<ProductDTO> result = underTest.getProductByCategory(category);

        // Then
        List<ProductDTO> expected = Arrays.asList(productDTO1, productDTO2);
        assertEquals(expected, result);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).findProductsByCategory(category);
        verify(productDTOMapper, times(2)).apply(productArgumentCaptor.capture());
    }

    @Test
    void canGetProductsByPriceWithCategory() {
        // Given
        String category = ProductCategories.CUP.toString();
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.getProductsByPriceWithCategory(category)).thenReturn(products);

        ProductDTO productDTO1 = new ProductDTO(1L, "Title1", "Description1", "Image1", "Image2", "Image3", "Image4", 10.0, "CHF");
        ProductDTO productDTO2 = new ProductDTO(2L, "Title2", "Description2", "Image1", "Image2", "Image3", "Image4", 20.0, "CHF");

        when(productDTOMapper.apply(product1)).thenReturn(productDTO1);
        when(productDTOMapper.apply(product2)).thenReturn(productDTO2);

        // When
        List<ProductDTO> result = underTest.getProductsByPriceWithCategory(category);

        // Then
        List<ProductDTO> expected = Arrays.asList(productDTO1, productDTO2);
        assertEquals(expected, result);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).getProductsByPriceWithCategory(category);
        verify(productDTOMapper, times(2)).apply(productArgumentCaptor.capture());
    }

    @Test
    void canGetProductsBySoldWithCategory() {
        // Given
        String category = ProductCategories.CUP.toString();
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.getProductsBySoldWithCategory(category)).thenReturn(products);

        when(productDTOMapper.apply(product1)).thenReturn(productDTO1);
        when(productDTOMapper.apply(product2)).thenReturn(productDTO2);

        // When
        List<ProductDTO> result = underTest.getProductsBySoldWithCategory(category);

        // Then
        List<ProductDTO> expected = Arrays.asList(productDTO1, productDTO2);
        assertEquals(expected, result);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).getProductsBySoldWithCategory(category);
        verify(productDTOMapper, times(2)).apply(productArgumentCaptor.capture());
    }

    @Test
    void canGetProductsByIds() {
        // Given
        Set<Long> productIds = new HashSet<>(Arrays.asList(1L, 2L));
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);

        when(productRepository.findProductById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.findProductById(2L)).thenReturn(Optional.of(product2));

        when(productDTOMapper.apply(product1)).thenReturn(productDTO1);
        when(productDTOMapper.apply(product2)).thenReturn(productDTO2);

        // When
        List<ProductDTO> result = underTest.getProductsByIds(productIds);

        // Then
        List<ProductDTO> expected = Arrays.asList(productDTO1, productDTO2);
        assertEquals(expected, result);

        verify(productRepository).findProductById(1L);
        verify(productRepository).findProductById(2L);
        verify(productDTOMapper).apply(product1);
        verify(productDTOMapper).apply(product2);
    }
}