using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Coordination.Model 
{
    public class Job : DatabaseObject
    {
        
        public  string Latitude
        {
            get; set;            
        }
        public  string Longitude
        {
            get; set;                         
        }
        public  string Description
        {
            get; set;            
        }
        public  bool? Completed
        {
            get; set;
        }
        
        public  string JobName
        {
            get; set;
          
        }
        public virtual ICollection<JobAssignment> JobAssignments
        {
            get; set;
        }
    }
}
