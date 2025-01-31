import { Item } from "./Item";

export interface Case {
    id: number;
    name: string;
    img: string;
    price: number;
    items: Item[];
}
