package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CompanyCustomerService;
import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedCarRequest;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedCompanyCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedCompanyCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedCompanyCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetCompanyCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllCompanyCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedCompanyCustomerResponse;
import com.turkcell.rentacar.business.rules.CompanyCustomerBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.CompanyCustomerRepository;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.CompanyCustomer;
import com.turkcell.rentacar.entities.concretes.Customer;
import com.turkcell.rentacar.entities.enums.CustomerType;
import org.modelmapper.TypeToken;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CompanyCustomerManager implements CompanyCustomerService {

   private CompanyCustomerRepository companyCustomerRepository;
   private ModelMapperService modelMapperService;
   private CompanyCustomerBusinessRules companyCustomerBusinessRules;
   private CustomerService customerService;


    @Override
    public CreatedCompanyCustomerResponse add(CreatedCompanyCustomerRequest createdCompanyCustomerRequest) {
        CreatedCustomerResponse createdCustomerResponse =customerService.add(new CreatedCustomerRequest(CustomerType.COMPANY));
        Customer customer=modelMapperService.forResponse().map(createdCustomerResponse,Customer.class);
        CompanyCustomer companyCustomer=modelMapperService.forRequest().map(createdCompanyCustomerRequest,CompanyCustomer.class);
        companyCustomer.setCreatedDate(LocalDateTime.now());
        companyCustomer.setCustomer(customer);
        CompanyCustomer createdCompanyCustomer = companyCustomerRepository.save(companyCustomer);
        return modelMapperService.forResponse().map(createdCompanyCustomer,CreatedCompanyCustomerResponse.class);
        /*bir müşteri oluşturma işleminin gerçekleştirilmesini ve sonucunda oluşturulan müşterinin bilgilerini içeren
        bir yanıt nesnesini döndürme işlemini sağlar. Müşteri oluşturma işlemi, istemciden gelen istek verilerinin alınması,
         müşteri nesnesine dönüştürülmesi, oluşturulma tarihinin ayarlanması ve veritabanına kaydedilmesi adımlarını içerir. Sonuç olarak,
          oluşturulan müşterinin bilgileri, istemciye döndürülen bir yanıt nesnesi aracılığıyla geri gönderilir.
         */
    }

    @Override
    public UpdatedCompanyCustomerResponse update(int id,UpdatedCompanyCustomerRequest updatedCompanyCustomerRequest) {
       return null;   // veirlen id ye göre güncelleme  hata veriyor id yi bulamıyor
    }

    @Override
    public void delete(int id) {
        Optional<CompanyCustomer> foundOptionalCompanyResponse = companyCustomerRepository.findById(id);
        companyCustomerBusinessRules.companyCustomerShouldBeExist(foundOptionalCompanyResponse);
        companyCustomerRepository.delete(foundOptionalCompanyResponse.get());
        // id alır ve silme işlemi yapar
    }

    @Override
    public GetAllCompanyCustomerResponse getById(int id) {
       return null;

        //belirtilen id ye sahip müşteri var mı yok mu
    }

    @Override
    public List<GetAllCompanyCustomerResponse> getAll() {
        List<CompanyCustomer> companyCostumers = companyCustomerRepository.findAll();
        return modelMapperService.forResponse().map(companyCostumers, new TypeToken<List<GetAllCompanyCustomerResponse>>() {
        }.getType());
        // tüm müşterileri döndürür
    }
}
