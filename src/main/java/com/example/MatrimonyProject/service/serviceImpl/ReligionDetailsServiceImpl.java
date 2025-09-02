package com.example.MatrimonyProject.service.serviceImpl;

import com.example.MatrimonyProject.dto.ReligionDetailsDTO;
import com.example.MatrimonyProject.exception.ResourceNotFoundException;
import com.example.MatrimonyProject.mapper.ReligionDetailsMapper;
import com.example.MatrimonyProject.model.ReligionDetails;
import com.example.MatrimonyProject.model.UserProfile;
import com.example.MatrimonyProject.repo.ReligionDetailsRepo;
import com.example.MatrimonyProject.repo.UserProfileRepo;
import com.example.MatrimonyProject.service.ReligionDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReligionDetailsServiceImpl implements ReligionDetailsService {

    private final ReligionDetailsRepo religionDetailsRepository;
    private final ReligionDetailsMapper religionDetailsMapper;
    private final UserProfileRepo userProfileRepository;


    //To Create
    @Override
    public ReligionDetailsDTO create(Long userId, ReligionDetailsDTO dto) {
        UserProfile user = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        ReligionDetails entity = religionDetailsMapper.toEntity(dto);
        entity.setUser(user);

        ReligionDetails saved = religionDetailsRepository.save(entity);
        user.setReligionDetails(saved);
        userProfileRepository.save(user);

        return religionDetailsMapper.toDto(saved);
    }


    //To Update
    @Override
    public ReligionDetailsDTO update(Long id, ReligionDetailsDTO dto) {
        religionDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ReligionDetails not found with id " + id));

        dto.setId(id);
        ReligionDetails updated = religionDetailsMapper.toEntity(dto);
        return religionDetailsMapper.toDto(religionDetailsRepository.save(updated));
    }


    //To Get the Data by the Id
    @Override
    public ReligionDetailsDTO getById(Long id) {
        return religionDetailsRepository.findById(id)
                .map(religionDetailsMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("ReligionDetails not found with id " + id));
    }


    //To Get all the data
    @Override
    public List<ReligionDetailsDTO> getAll() {
        return religionDetailsRepository.findAll()
                .stream()
                .map(religionDetailsMapper::toDto)
                .toList();
    }


    //To delete the Data by Id
    @Override
    public void delete(Long id) {
        if (!religionDetailsRepository.existsById(id)) {
            throw new ResourceNotFoundException("ReligionDetails not found with id " + id);
        }
        religionDetailsRepository.deleteById(id);
    }
}
