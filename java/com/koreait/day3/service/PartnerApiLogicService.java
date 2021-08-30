package com.koreait.day3.service;

import com.koreait.day3.ifs.CrudInterface;
import com.koreait.day3.model.entity.OrderGroup;
import com.koreait.day3.model.entity.Partner;
import com.koreait.day3.model.entity.Users;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.Pagination;
import com.koreait.day3.model.network.request.PartnerApiRequest;
import com.koreait.day3.model.network.request.UserApiRequest;
import com.koreait.day3.model.network.response.*;
import com.koreait.day3.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //서비스로직어노테이션-> 서비스 레이어 , 내부에서 자바 로직을 처리함
@RequiredArgsConstructor
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {
    private final PartnerRepository partnerRepository;
    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        return null;
    }

    @Override
    public Header<PartnerApiResponse> read(Long id){
        return null;
    }


    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
       return null;
    }

    @Override
    public Header delete(Long id) {
        Optional<Partner> optional = partnerRepository.findById(id);

        return optional.map(partner -> {
            partnerRepository.delete(partner);
            return Header.OK();

        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private PartnerApiResponse response(Partner partner){
        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()

                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .regDate(partner.getRegDate())
                .build();

        return partnerApiResponse;
    }
    public Header<List<PartnerApiResponse>> search(Pageable pageable){
        Page<Partner> partners = baseRepository.findAll(pageable);
        List<PartnerApiResponse> partnerApiResponseList = partners.stream()
                .map(partner -> response(partner))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(partners.getTotalPages())
                .totalElements(partners.getTotalElements())
                .currentPage(partners.getNumber())
                .currentElements(partners.getNumberOfElements())
                .build();
        return Header.OK(partnerApiResponseList, pagination);
    }
}
