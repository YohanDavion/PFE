package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.dto.UserUpdateDTO;
import fr.limayrac.pfeback.model.Orthophoniste;

public interface IOrthophonisteService extends IAbstractService<Orthophoniste> {
    void updateOrthophoniste(UserUpdateDTO userDto);
}
