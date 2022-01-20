package com.geekbrains.spring.web.controllers.endpoints;

import com.geekbrains.spring.web.services.ProductsService;
import com.geekbrains.spring.web.soap.products.GetAllProductsRequest;
import com.geekbrains.spring.web.soap.products.GetAllProductsResponse;
import com.geekbrains.spring.web.soap.products.GetProductByIdRequest;
import com.geekbrains.spring.web.soap.products.GetProductByIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductsEndpoint {
    private static final String NAMESPACE_URI = "http://ru.kgogolev.com/spring/ws/products";
    private final ProductsService productsService;

    /* Запрос
         POST http://localhost:8189/app/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:p="http://ru.kgogolev.com/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <p:GetProductByIdRequest>
                    <p:id>1</p:id>
                </p:GetProductByIdRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetProductByIdRequest")
    @ResponsePayload
    @Transactional
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productsService.findByIdSOAP(request.getId()));
        return response;
    }

    /* Запрос
     POST http://localhost:8189/app/ws

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
      xmlns:p="http://ru.kgogolev.com/spring/ws/products">
        <soapenv:Header/>
        <soapenv:Body>
            <p:GetAllProductsRequest/>
        </soapenv:Body>
        </soapenv:Envelope>
 */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllProductsRequest")
    @ResponsePayload
    @Transactional
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productsService.findAllSOAP().forEach(response.getProducts()::add);
        return response;
    }



}
