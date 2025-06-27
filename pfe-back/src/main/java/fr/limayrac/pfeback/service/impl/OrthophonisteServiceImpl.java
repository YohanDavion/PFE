package fr.limayrac.pfeback.service.impl;

import fr.limayrac.pfeback.dto.UserUpdateDTO;
import fr.limayrac.pfeback.model.Orthophoniste;
import fr.limayrac.pfeback.repository.OrthophonisteRepository;
import fr.limayrac.pfeback.service.IOrthophonisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class OrthophonisteServiceImpl implements IOrthophonisteService {
    @Autowired
    private OrthophonisteRepository orthophonisteRepository;
    @Override
    public List<Orthophoniste> findAll() {
        return orthophonisteRepository.findAll();
    }

    @Override
    public Orthophoniste findById(Long id) {
        return orthophonisteRepository.findById(id).orElse(null);
    }

    @Override
    public Orthophoniste save(Orthophoniste orthophoniste) {
        return orthophonisteRepository.save(orthophoniste);
    }

    @Override
    public void delete(Orthophoniste orthophoniste) {
        orthophonisteRepository.delete(orthophoniste);
    }

    @Override
    public void delete(Long id) {
        orthophonisteRepository.deleteById(id);
    }

    public void updateOrthophoniste(UserUpdateDTO dto) {
        Orthophoniste o = orthophonisteRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Orthophoniste non trouvé"));

        o.setNom(dto.getNom());
        o.setPrenom(dto.getPrenom());
        o.setLogin(dto.getEmail());
        o.setTelephone(dto.getTelephone());
        o.setAdresse(dto.getAdresse());
        o.setRPPS(dto.getRpps());
        o.setSIRET(dto.getSiret());

        if (dto.getPhoto() != null && dto.getPhoto().startsWith("data:image")) {
            o.setPhoto(decodeBase64Image(dto.getPhoto()));
        }

        orthophonisteRepository.save(o);
    }

    private byte[] decodeBase64Image(String base64Image) {
        String[] parts = base64Image.split(",");
        return Base64.getDecoder().decode(parts[1]);
    }
}
