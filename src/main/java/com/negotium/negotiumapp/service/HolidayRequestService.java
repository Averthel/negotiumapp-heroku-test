package com.negotium.negotiumapp.service;

import com.negotium.negotiumapp.exception.DuplicateRequestIdException;
import com.negotium.negotiumapp.exception.HolidayRequestRemoveException;
import com.negotium.negotiumapp.model.user.employee.Employee;
import com.negotium.negotiumapp.model.user.employee.request.HolidayRequest;
import com.negotium.negotiumapp.model.user.employee.request.HolidayRequestDto;
import com.negotium.negotiumapp.model.user.employee.request.HolidayRequestMapper;
import com.negotium.negotiumapp.repository.EmployeeRepository;
import com.negotium.negotiumapp.repository.HolidayRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HolidayRequestService {

    @Autowired
    private HolidayRequestRepository holidayRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public HolidayRequestService(HolidayRequestRepository holidayRequestRepository, EmployeeRepository employeeRepository) {
        this.holidayRequestRepository = holidayRequestRepository;
        this.employeeRepository = employeeRepository;
    }

    public HolidayRequestDto saveRequest(HolidayRequestDto holidayRequestDto) {
        Optional<HolidayRequest> findByRequestId = holidayRequestRepository.findById(holidayRequestDto.getId());
        findByRequestId.ifPresent(x -> {
            throw new DuplicateRequestIdException("Request with this ID is already exist");
        });
        Optional<Employee> findOneEmployeeByIndex = employeeRepository
                .findByEmployeeIndex(holidayRequestDto.getEmployee().getEmployeeIndex());
        findOneEmployeeByIndex.ifPresentOrElse(
                holidayRequestDto::setEmployee,
                () -> {
                    throw new NullPointerException("Employee not found");
                }
        );
        return mapAndSaveRequest(holidayRequestDto);
    }

    public HolidayRequestDto updateRequest(HolidayRequestDto holidayRequestDto) {
        Long id = holidayRequestDto.getId();
        Optional<HolidayRequest> foundByRequestId =
                holidayRequestRepository.findById(id);
        if (foundByRequestId.isEmpty()) {
            throw new NullPointerException("Request not found");
        }
        foundByRequestId.ifPresent(x -> {
            if (!(x.getId()).equals(id)) {
                throw new DuplicateRequestIdException("Request with this ID does not exist");
            }
        });
        return mapAndSaveRequest(holidayRequestDto);
    }

    private HolidayRequestDto mapAndSaveRequest(HolidayRequestDto holidayRequestDto) {
        HolidayRequest holidayRequestEntity = HolidayRequestMapper.toEntity(holidayRequestDto);
        HolidayRequest savedRequest = holidayRequestRepository.save(holidayRequestEntity);
        return HolidayRequestMapper.toDto(savedRequest);
    }

    public void deleteById(Long id) {
        Optional<HolidayRequest> findByRequestId = holidayRequestRepository.findById(id);
        findByRequestId.ifPresentOrElse(x ->
                        holidayRequestRepository
                                .deleteById(id),
                () -> {
                    throw new HolidayRequestRemoveException(
                            "The request could not be deleted. Please try again later");
                });
    }

    public List<HolidayRequestDto> findAllByEmployeeName(String name) {
        return holidayRequestRepository.findAllByEmployeeName(name)
                .stream()
                .map(HolidayRequestMapper::toDto)
                .collect(Collectors.toList());
    }


    public List<HolidayRequestDto> findAll() {
        return holidayRequestRepository.findAll()
                .stream()
                .map(HolidayRequestMapper::toDto)
                .collect(Collectors.toList());
    }


    public Optional<HolidayRequestDto> findById(Long id) {
        return holidayRequestRepository.findById(id).map(HolidayRequestMapper::toDto);
    }
}