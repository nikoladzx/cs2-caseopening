import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import SlotItem from 'src/app/models/SlotItem';
import { AdminService } from 'src/app/services/admin/admin.service';
import { ImgService } from 'src/app/services/img/img.service';

@Component({
  selector: 'app-admin-slot-item',
  templateUrl: './admin-slot-item.component.html',
  styleUrls: ['./admin-slot-item.component.css']
})
export class AdminSlotItemComponent implements OnInit {

   slotItem: SlotItem = { id: 0, imagepath: '', multiplier: 1 };
    selectedFile: File | null = null;
    message: string | null = null;
    slotId: string | null = null;
  
    constructor(private adminService: AdminService, private imgService: ImgService, private route: ActivatedRoute) { }
  
    ngOnInit(): void {
        
        this.slotId=this.route.snapshot.paramMap.get('id');
     }
  
    onFileChange(event: any): void {
      if (event.target.files.length > 0) {
        this.selectedFile = event.target.files[0];
      }
    }
  
    onSubmit(event: Event): void {
      event.preventDefault();
      if (this.selectedFile) {
        this.imgService.uploadImage(this.selectedFile).subscribe({
          next: (imageUrl: string) => {
            console.log(imageUrl);
            this.slotItem.imagepath = imageUrl;  
            this.addSlotItem();
          },
          error: (error) => {
            console.error(error);
            this.message = 'Image upload failed!';
          }
        });
      }
    }
  
    addSlotItem(): void {
      this.adminService.addSlotItem(this.slotItem, parseInt(this.slotId!)).subscribe({
        next: () => {
          this.message = "Slot added successfully!";
          console.log("Slot added");
        },
        error: (error) => {
          console.error(error);
          this.message = "Failed to add slot!";
        }
      });
    }
}
