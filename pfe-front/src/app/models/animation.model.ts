import {Serie} from './serie.model';
import {Media} from './media.model';

export interface Animation {
    id: number;
    active: string;
    gif: Media;
    image: Media;
    son: Media;
}
