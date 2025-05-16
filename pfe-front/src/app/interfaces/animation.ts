import {Media} from '../models/media.model';

export class Animation {
  id: number;
  active: boolean;
  gif: Media;
  image: Media;
  son: Media;

    constructor(id: number,active: boolean, gif : Media, image:Media, son: Media){
        this.id=id;
        this.active=active;
        this.gif = gif;
        this.image = image;
        this.son = son;
    }
}
