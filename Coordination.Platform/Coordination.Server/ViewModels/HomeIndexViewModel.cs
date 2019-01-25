using Coordination.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Coordination.Server.ViewModels
{
    public class HomeMapViewModel
    {
        public List<Job> Jobs { get; set; }
        public List<User> Users { get; set; }
       
    }
}