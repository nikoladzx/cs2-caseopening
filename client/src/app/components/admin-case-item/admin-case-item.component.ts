import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Item } from 'src/app/models/Item';
import { AdminService } from 'src/app/services/admin/admin.service';
import { ImgService } from 'src/app/services/img/img.service';

@Component({
  selector: 'app-admin-case-item',
  templateUrl: './admin-case-item.component.html',
  styleUrls: ['./admin-case-item.component.css']
})
export class AdminCaseItemComponent implements OnInit {

     item: Item = { name: '', price: 0, img: '', type: 'BLUE', id:0, caseId: 0 };
     selectedFile: File | null = null;
     message: string | null = null;
     slotId: string | null = null;
   
     constructor(private adminService: AdminService, private imgService: ImgService, private route: ActivatedRoute) { }
   
     ngOnInit(): void {
         this.item.caseId=parseInt(this.route.snapshot.paramMap.get('id')!);
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
             this.item.img = imageUrl;  
             this.addCaseItem();
           },
           error: (error) => {
             console.error(error);
             this.message = 'Image upload failed!';
           }
         });
       }
     }
   
     addCaseItem(): void {
       this.adminService.addCaseItem(this.item).subscribe({
         next: () => {
           this.message = "Case item added successfully!";
           console.log("Item added");
         },
         error: (error) => {
           console.error(error);
           this.message = "Failed to add case item!";
         }
       });
     }
}
