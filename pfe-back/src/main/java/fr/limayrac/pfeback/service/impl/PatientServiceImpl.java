package fr.limayrac.pfeback.service.impl;

import fr.limayrac.pfeback.dto.UserUpdateDTO;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.repository.PatientRepository;
import fr.limayrac.pfeback.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class PatientServiceImpl implements IPatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public void updatePatient(UserUpdateDTO dto) {
        Patient patient = patientRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Patient non trouvé"));

        patient.setNom(dto.getNom());
        patient.setPrenom(dto.getPrenom());
        patient.setLogin(dto.getEmail());
        patient.setTelephone(dto.getTelephone());
        patient.setAdresse(dto.getAdresse());
        patient.setNomParent(dto.getNomParent());
        patient.setPrenomParent(dto.getPrenomParent());

        if (dto.getPhoto() != null && dto.getPhoto().startsWith("data:image")) {
            byte[] imageBytes = decodeBase64Image(dto.getPhoto());
            patient.setPhoto(imageBytes);
        }

        patientRepository.save(patient);
    }

    private byte[] decodeBase64Image(String base64Image) {
        String[] parts = base64Image.split(",");
        return Base64.getDecoder().decode(parts[1]);
    }

    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void delete(Patient patient) {
        patientRepository.delete(patient);
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}
