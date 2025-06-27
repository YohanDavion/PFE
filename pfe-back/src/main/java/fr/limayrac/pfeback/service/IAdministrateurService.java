package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.dto.UserUpdateDTO;
import fr.limayrac.pfeback.model.Administrateur;

public interface IAdministrateurService extends IAbstractService<Administrateur> {
    void updateAdministrateur(UserUpdateDTO userDto);
}
