# Solver-Problem-MLQ

## Overview
In the Multi-Level Queue (MLQ) scheduling algorithm, the system is composed of several independent queues, each managed by its own scheduling algorithm. Processes are assigned to a specific queue based on predefined criteria such as priority. This architecture enables efficient management of diverse processes (interactive, batch, and real-time) within a unified framework.  

This project, Solver-Problem-MLQ, implements a scheduling system based on the MLQ algorithm with the following inputs and outputs.  

---

## System Inputs

### 1. Queue Configuration
- Number of queues and the priority of each queue (higher-priority queues are served first).  
- A dedicated scheduling algorithm for each queue, selectable from:  
  - FCFS (First Come First Serve)  
  - SJF (Shortest Job First)  
  - SRTF (Shortest Remaining Time First)  
  - HRRN (Highest Response Ratio Next)  
  - Round Robin  
    - Slice Time (time quantum)  
    - Policy (priority handling between newly arrived and older processes)  

### 2. Process Table
- Number and names of processes  
- Arrival Time  
- Service Time  
- Priority (used to assign the process to the appropriate queue)  

---

## System Outputs
After processing the inputs, Solver-Problem-MLQ produces the following results:  
- Gantt Chart: graphical representation of process execution order over time  
- Average Waiting Time (AWT)  
- Average Turnaround Time (ATT)  

---

## Project Goal
This system serves as an educational and analytical simulator, providing deeper insight into multi-level scheduling mechanisms and the impact of different parameters on system performance.  

---

## Notes and Future Plans
- The user interface is still in its early stages; therefore, inputs must be entered carefully, and process priorities should be consistent with the defined queues.  
- Planned improvements for future versions include:  
  - Adding separate Gantt charts for each priority queue  
  - Implementing the MLFQ (Multi-Level Feedback Queue) algorithm  
  - Enhancing and optimizing the user interface for a better experience  

---

## Usage
To run the project from the terminal, navigate to the root folder and enter:

```bash
mvn clean javafx:run
```

---

# پروژه درس سیستم‌های عامل

## الگوریتم MLQ (صف‌های چندسطحی)
در الگوریتم MLQ، سیستم از چندین صف مستقل تشکیل شده است که هر کدام با الگوریتم زمان‌بندی اختصاصی خود مدیریت می‌شوند. فرآیندها بر اساس معیارهای از پیش تعیین‌شده‌ای مانند اولویت، به صف متناظر تخصیص می‌یابند. این معماری امکان مدیریت کارای فرآیندهای متنوع (از جمله فرآیندهای تعاملی، دسته‌ای و بلادرنگ) را در چارچوبی یکپارچه فراهم می‌سازد.  

این پروژه با عنوان Solver-Problem-MLQ، یک سیستم زمان‌بندی بر مبنای الگوریتم MLQ را با ورودی‌های زیر پیاده‌سازی می‌کند:

---

## مشخصات ورودی سیستم

### ۱. پیکربندی صف‌ها
- تعداد صف‌ها و اولویت هر صف (صف‌های با اولویت بالاتر زودتر سرویس می‌گیرند)  
- الگوریتم زمان‌بندی اختصاص‌یافته به هر صف (به صورت مجزا) که قابل انتخاب باشند:  
  - FCFS  
  - SJF  
  - SRTF  
  - HRRN  
  - Round Robin  
    - Slice Time (کوانتوم یا برش زمانی)  
    - Policy (نحوه‌ی اولویت‌گذاری بین فرآیندهای تازه‌وارد و قدیمی)  

### ۲. جدول فرآیندها
- تعداد و نام فرآیندها  
- Arrival Time (زمان ورود)  
- Service Time (زمان سرویس)  
- Priority (اولویت فرآیند برای تخصیص به صف مناسب)  

---

## خروجی سیستم
پس از پردازش ورودی‌های فوق، Solver-Problem-MLQ نتایج زیر را به‌عنوان خروجی ارائه می‌دهد:  
- نمودار گانت (Gantt Chart): نمایش گرافیکی ترتیب اجرای فرآیندها در طول زمان  
- Average Waiting Time (AWT): میانگین زمان انتظار  
- Average Turnaround Time (ATT): میانگین زمان بازگشت  

---

## هدف پروژه
این سیستم به‌عنوان یک شبیه‌ساز آموزشی و تحلیلی، درک عمیق‌تری از مکانیزم‌های زمان‌بندی چندصفی و تأثیر پارامترهای مختلف بر عملکرد سیستم ارائه می‌دهد.  

---

## نکات و برنامه‌های آینده
- رابط کاربری هنوز در مراحل اولیه است؛ بنابراین هنگام استفاده لازم است اطلاعات را با دقت وارد کنید و شماره‌ی اولویت فرآیندها با صف‌های تعریف‌شده سازگار باشد.  
- در نسخه‌های آینده، بهبودهای زیر مدنظر قرار دارد:  
  - افزودن امکان رسم گانت چارت جداگانه برای هر صف اولویت  
  - پیاده‌سازی کامل الگوریتم MLFQ  
  - تقویت و بهینه‌سازی رابط کاربری برای تجربه‌ی بهتر

  ---

## Usage
برای اجرای پروژه در ترمینال و از پوشه‌ی اصلی، دستور زیر را وارد کنید:

```bash
mvn clean javafx:run
```